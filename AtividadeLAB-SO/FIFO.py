import psutil

class Process:
    def __init__(self, pid):
        self.pid = pid
        self.process = psutil.Process(pid)
        self.nome = self.process.name()
        self.prioridade = self.process.nice()
        self.estado = self.process.status()

    def executar(self):
        try:
            if self.estado == psutil.STATUS_STOPPED:
                self.process.resume()
                self.estado = self.process.status()
                print(f"Processo {self.nome} (PID: {self.pid}) foi retomado.")
            elif self.estado == psutil.STATUS_RUNNING:
                print(f"Processo {self.nome} (PID: {self.pid}) já está em execução.")
            else:
                print(f"Processo {self.nome} (PID: {self.pid}) não pode ser retomado. Estado atual: {self.estado}")
        except psutil.NoSuchProcess:
            print(f"O processo com PID {self.pid} não existe mais.")

    def finalizar(self):
        try:
            self.process.terminate()
            self.estado = self.process.status()
            print(f"Processo {self.nome} (PID: {self.pid}) foi finalizado.")
        except psutil.NoSuchProcess:
            print(f"O processo com PID {self.pid} não existe mais.")

    def __str__(self):
        return f"Processo {self.nome} (PID: {self.pid}, Prioridade: {self.prioridade}, Estado: {self.estado})"

    @staticmethod
    def listar_processos():
        processos = []
        for proc in psutil.process_iter(['pid', 'name', 'status']):
            try:
                processos.append(f"PID: {proc.info['pid']}, Nome: {proc.info['name']}, Estado: {proc.info['status']}")
            except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
                pass
        return processos

def main():
    processos = Process.listar_processos()
    for proc in processos:
        print(proc)
    pid_exemplo = int(input("\nInsira um PID de processo para manipular: "))
    processo_exemplo = Process(pid_exemplo)
    print(processo_exemplo)
    processo_exemplo.executar()
    processo_exemplo.finalizar()

if __name__ == "__main__":
    main()
