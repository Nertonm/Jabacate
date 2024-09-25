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
    
def main():
    choice = int(input("Escolha: "))
    if choice == 1: aula2_question1()
    elif choice == 2: aula2_question2()
    elif choice == 3: aula2_question3()
    elif choice == 4: aula2_question4()

if __name__ == '__main__':
    main()