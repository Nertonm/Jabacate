#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

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
        novo->pid = pid;
        novo->prox = NULL;

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
            Proc temp = aux;
            aux = aux->prox;
            free(temp); 
        }
        free(fila);
    }
}

int main(void) {
    Fila fila = filaCriar();
    if (!fila) {
        perror("Erro ao criar a fila");
        return 1;
    }

    int choice = 1;

    while (1) {
        printf("Escolha uma opção: 1 para inserir, 2 para exibir, 0 para sair: ");
        scanf("%i", &choice);
        switch (choice){    
        case 1: {
            filaInserir(fila);
        case 2: {
            filaExibir(fila);
        case 0: {
            break; 
        default:
            printf("Opção inválida.\n");
        }
    }

    return 0;
}

