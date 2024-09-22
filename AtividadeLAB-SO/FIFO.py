import os
import psutil
import time
import random

def executar_processo(pid, tempo_execucao):
    print(f"Processo {pid} iniciado com tempo de execução: {tempo_execucao:.2f} segundos.")
    time.sleep(tempo_execucao)
    print(f"Processo {pid} concluído.")

def fifo(processos):
    print("\nExecutando Escalonamento FIFO:")
    for pid, tempo in processos:
            executar_processo(pid, tempo)

def main():
    processos = []
    n = int(input("Digite o número de processos: "))
    for i in range(n):
        tempo_execucao = random.uniform(1, 5)  # Tempo de execução aleatório entre 1 e 5 segundos
        processos.append((i + 1, tempo_execucao))
        print(f"Processo {i + 1} criado com tempo de execução: {tempo_execucao:.2f} segundos.")
    print("""
        Selecione o escalanador:
        1- FIFO
        2- SJF
        3- ROUND ROBIN
        4- PRIORIDADE
    """)
    while True:
        escolha = input("Digite sua escolha ou 'sair' para encerrar: ")
        if escolha.lower() == 'sair':
            break
        else:
            if escolha == "1":
                fifo(processos)

if __name__ == "__main__":
    main()
