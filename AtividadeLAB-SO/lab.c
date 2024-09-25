#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>

typedef struct proc *Proc;
struct proc {
    pid_t pid; 
    int time;
    int nice;
    Proc prox; 
};

typedef struct procs {
    Proc ini; 
    Proc fim; 
    int qtd;
} *Fila;

pid_t procCreate(void) {
    return fork();
}

Fila filaCriar(void) {
    Fila fila = (Fila)malloc(sizeof(struct procs)); 
    if (fila) {
        fila->ini = NULL;
        fila->fim = NULL;
        fila->qtd = 0;
    }
    return fila;
}

int filaExibir(Fila fila) {
    if (!fila || !fila->ini) {
        printf("Fila vazia.\n");
        return 0;
    }
    
    Proc aux = fila->ini;
    while (aux) {
        printf("[%d]->", aux->pid);
        aux = aux->prox;
    }
    printf("NULL\n"); 
    return 1;
}

int filaInserir(Fila fila) {
    if (!fila) return 0; 

    pid_t pid = procCreate();
    if (pid > 0) { // Apenas no processo pai
        Proc novo = (Proc)malloc(sizeof(struct proc));
        if (!novo) {
            perror("Erro ao alocar memória para novo processo");
            return 0;
        }
        int random_number = (rand() % 5) + 1;
        novo->pid = pid;
        novo->time = random_number;
        novo->nice = 0;
        novo->prox = NULL;
        printf("\nCriando process %d com %i segundos para execução\n",pid,random_number);
        if (!fila->ini) { ;
            fila->ini = novo;
            fila->fim = novo;
        } else { 
            fila->fim->prox = novo;
            fila->fim = novo;
        }
        fila->qtd++;
        return 1;
    } else if (pid == 0) {
        exit(0); 
    }
    return 0; 
}

void filaLiberar(Fila fila) {
    if (fila) {
        Proc aux = fila->ini;
        while (aux) {
            kill(aux->pid,SIGKILL);
            Proc temp = aux;
            aux = aux->prox;
            free(temp); 
        }
        free(fila);
    }
}

void FIFO(Fila fila) {
    if (fila) {
        Proc aux = fila->ini;
        while (aux) {
            printf("\nExecutando processo %d com %i segundos de execução\n", aux->pid, aux->time);
            sleep(aux->time);
            kill(aux->pid,SIGKILL);
            Proc temp = aux;
            aux = aux->prox;
            free(temp); 
        }
        free(fila);
    }
}
void SJF(Fila fila) {
    if (fila && fila->ini) {
        Proc aux, aux2, prev_aux;
        int trocado;
        do {
            trocado = 0;
            aux = fila->ini;
            prev_aux = NULL;
            while (aux && aux->prox) {
                aux2 = aux->prox;
                if (aux->time > aux2->time) {
                    aux->prox = aux2->prox;
                    aux2->prox = aux;
                    if (prev_aux == NULL) {
                        fila->ini = aux2;
                    } else {
                        prev_aux->prox = aux2;
                    }
                    trocado = 1;
                    prev_aux = aux2;
                } else {
                    prev_aux = aux;
                    aux = aux->prox;
                }
            }
        } while (trocado);
        aux = fila->ini;
        while (aux) {
            printf("\nExecutando processo %d com %i segundos de execução", aux->pid, aux->time);
            sleep(aux->time);  
            Proc temp = aux;
            aux = aux->prox;
            free(temp);
        }
    }
}

void round_robin(Fila fila, int quantum){
if (fila->ini) {
        Proc aux = fila->ini;
        Proc ant = NULL;
        fila->fim->prox = fila->ini;
        do{
            printf("\nExecutando processo %d com %i segundos de execução com quantum de %i", aux->pid, aux->time, quantum);
            if (quantum >= aux->time && aux->time > 0){
                sleep(aux->time);
                kill(aux->pid,SIGKILL);
                aux->time = 0;
                printf("\nProcesso %d terminado", aux->pid);
                aux = aux->prox;
            }
            else if(aux->time > 0){
                sleep(quantum);
                aux->time -= quantum;
            }
            aux = aux->prox;
        }while(aux != fila->ini);
        free(fila);
    }
}

void Prioridade(Fila fila){
    if (fila && fila->ini) {
        Proc aux, aux2, prev_aux;
        int trocado;
        do {
            trocado = 0;
            aux = fila->ini;
            prev_aux = NULL;
            while (aux && aux->prox) {
                aux2 = aux->prox;
                if (aux->nice  <aux2->nice) {
                    aux->prox = aux2->prox;
                    aux2->prox = aux;
                    if (prev_aux == NULL) {
                        fila->ini = aux2;
                    } else {
                        prev_aux->prox = aux2;
                    }
                    trocado = 1;
                    prev_aux = aux2;
                } else {
                    prev_aux = aux;
                    aux = aux->prox;
                }
            }
        } while (trocado);
        aux = fila->ini;
        while (aux) {
            printf("\nExecutando processo %d com %i segundos de execução e prioridade %i", aux->pid, aux->time, aux->nice);
            sleep(aux->time);  
            Proc temp = aux;
            aux = aux->prox;
            free(temp);
        }
    }
}

void definirPrioridade(Fila fila){
    int choice = 0;
    printf("Escolha um processo da lista:\n");
    filaExibir(fila);
    do{
        scanf("%i",&choice);
    }while(choice > fila->qtd || choice < 0);
    choice--;
    Proc aux = fila->ini;
    for (int i = 0; i < choice; i++)
       aux = aux->prox;
    printf("Nivel de Prioridade: ");
    scanf("%i",&choice);
    aux->nice = choice;
    return;
}

int main(void) {
    Fila fila = filaCriar();
    if (!fila) {
        perror("Erro ao criar a fila");
        return 1;
    }

    int choice = 1;
    int quantum = 0;
    while (1) {
        printf("\nEscolha uma opção:\n0 para sair\n1 para criar um processo\n2 para atribuir nice\n3 para exibir\n4 para FIFO\n5 para SJF\n6 para Round Robin\n7 para Prioridade\n");
        if (!fila) {
            Fila fila = filaCriar();
        }
        scanf("%i", &choice);
        switch (choice){    
            case 0: {
                filaLiberar(fila); 
                Fila fila = filaCriar();
                break;
            }
            case 1: { 
                filaInserir(fila);
                break;
            }
            case 2: {
                definirPrioridade(fila);
                break;
            }
            case 3: { 
                filaExibir(fila);
                break;
            }
            case 4: {
                FIFO(fila);
                Fila fila = filaCriar();
                break;            
            }
            case 5: {
                SJF(fila);
                Fila fila = filaCriar();
                break;
            }
            case 6: {
                printf("Valor quantum? ");
                scanf("%i", &quantum);
                round_robin(fila, quantum);
                Fila fila = filaCriar();
                break;
            }
            case 7: {
                Prioridade(fila);
                Fila fila = filaCriar();
                break;
            }
            default: {
                printf("Opção inválida.\n");
                break;
            }
        }
    }

    return 0;
}

