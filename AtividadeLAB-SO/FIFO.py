import os
import psutil

def make_dummy_process(lst):
    for i, value in enumerate(lst):
        pid = os.fork()
        if pid == 0:
            # Processo filho
            print(f"Processo filho {i} - valor da lista: {value}, PID: {os.getpid()}, PPID: {os.getppid()}")
            os._exit(0)  # Termina o processo filho após realizar seu trabalho
        else:
            # Processo pai
            print(f"Processo pai criou processo filho {i} com PID: {pid}")


def main():
    tamanho = int(input("Digite o tamanho da lista: "))
    lst = list(range(1, tamanho + 1))
    make_dummy_process(lst)
    print("""
        Selecione o escalanador:
        1- FIFO
        2- SJF
        3- ROUND ROBIN
        4- PRIORIDADE
    """)


if __name__ == "__main__":
    main()
