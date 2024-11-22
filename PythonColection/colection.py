def aula2_question1():
    name = input("What is your name? ")
    print("Hello " + str(name) + " Welcome!!")

def aula2_question2():
    tabela = []
    try:
        tamanho = int(input("Quantos itens adicionar? "))
    except ValueError:
        return
    for x in range(tamanho):
        item = str(input("Qual item adicionar? "))
        while True:
            try:
                preco = float(input("Preço: "))
                break
            except ValueError:
                print("Valor Invalido")
        turpla  = (item, preco)
        tabela.append(turpla)
    print(f"{'Produto':<20} {'Preço':>10}")
    for item, preco in tabela:
        print(f"{item:<20} {preco:>10.2f}")

def aula2_question3():
    choice = []
    for x in range(3):
        choice.append(input("Digite 3 notas para calcular media: "))
    media = int(0)
    for nota in choice:
        media += int (nota) / 3
    print(f"Media: {media:.2f}")

def aula2_question4():
    turple = (input("Digite seu nome: "),
              input("Digite sua data de Nascimento: "),
              input("Digite seu email: "))
    print(f"""
        \nDados Inseridos:
        Nome: {turple[0]}
        Data de Nascimento: {turple[1]}
        E-mail: {turple[2]}
    """)

def aula2_question5():
    print(input("Digite seu nome:") + " ,daqui a 5 anos você terá "
    , int(input("Digite sua idade: "))+ 5)

def aula2_question6():
    celsius = input("Graus celsius: ")
    print( celsius + " graus Celsius é equivalente a " ,
           ((int(celsius) * 9))/5 +32 ,"graus Fahrenheit.")

def aula2_question7():
    segundos = int(input("Numero de segundos: "))
    print(segundos // 3600,":",(segundos % 3600)//60)

def aula2_question8():
    yrs = int(input("Quantos anos você viveu? "))
    print(f"{yrs*12} meses "
          f"{yrs*52} semanas"
          f" {yrs*365} dias")

def aula2_question9():
    dois_numeros = (int(input("Digite dois numeros: ")),int(input("")))
    print(f"Soma: {dois_numeros[0] + dois_numeros[1]}, "
         f"Subtração: {dois_numeros[0] - dois_numeros[1]}, "
         f"Multiplicação: {dois_numeros[0] * dois_numeros[1]}, "
         f"Divisão: {dois_numeros[0] / dois_numeros[1]}")

def aula2_question10():
    print(f"{int(input("Raid do Circulo: ")) ** 2 * 3.14159}" + " área do circulo")

def aula2_question11():
    turple = (input("Qual o seu nome? "),
              input("Onde você mora? "))
    print(f"Olá,{turple[0]}! Seja bem-vindo a {turple[1]}")

def aula2_question12():
    x = int(input("Digite um numero: "))
    y = int(input("Digite outro numero: "))
    print(f"A soma de {x} e {y} é {x+y}")

def aula2_question13():
    turple = (input("Qual o seu Nome? "),
              input("Qual o seu Cargo? "),
              float(input("Qual o seu Salario? ")),)
    print(f"Funcionário: {turple[0]}, Cargo: {turple[1]}, Salário: R$ {turple[2]:.2f}")

def FIFO(lista):
    for x in range(len(lista)):
        proc = lista.pop(0)
        print(f"Executando o processo {proc}")

def SJF(lista):
    lista.sort()
    FIFO(lista)

def rounnd_roblin(lista):
    quantum = int(input("Quantum? "))
    while lista:
        for indice, x in enumerate(lista):
            if x - quantum <= 0:
                print(f"Elemento {listaa[indice]} saindo")
                lista.pop(indice)
            else:
                lista[indice] -= quantum
def prioriade(lista):
    lista.sort()
    i = 0
    while 1lista:
        print(f"Terminando o process {lista[-1]}")
        lista.pop(-1)

def main():
    lista = [1,321,212,2313,212,2321,212]
    choice = int(input("Escolha: "))
    if choice == 1 : aula2_question1()
    elif choice == 2 : aula2_question2()
    elif choice == 3 : aula2_question3()
    elif choice == 4 : aula2_question4()
    elif choice == 5 : aula2_question5()
    elif choice == 6 : aula2_question6()
    elif choice == 7 : aula2_question7()
    elif choice == 8 : aula2_question8()
    elif choice == 9 : aula2_question9()
    elif choice == 10 : aula2_question10()
    elif choice == 11 : aula2_question11()
    elif choice == 12 : aula2_question12()
    elif choice == 13 : aula2_question13()
    elif choice == 14 : FIFO(lista)
    elif choice == 15 : SJF(lista)
    elif choice == 16 : rounnd_roblin(lista)
    elif choice == 17 : prioriade(lista)

if __name__ == '__main__':
    main()