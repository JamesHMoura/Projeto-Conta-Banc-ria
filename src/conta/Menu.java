package conta;
import java.util.*;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.*;
import java.io.IOException;
public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		
		Scanner leia = new Scanner(System.in);
		
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		System.out.println("\nCriar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(),123,1,"João Cleber", 1000f, 100f );
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(),124,1,"Cleide Maria", 2000f, 100f );
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(),125,2,"Maria dos Santos", 4000f,12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(),126,2,"Juliana Ramos", 8000f,15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		while(true) {
			System.out.println(Cores.TEXT_BLUE_BRIGHT + Cores.ANSI_BLACK_BACKGROUND+"""
				*************************************************************************
				*                                                                       *
				*           BANCO DO BRAZIL COM Z                                       *
				*                                                                       *
				*************************************************************************
				*                                                                       *
				*           1 - Criar Conta                                             *
				*           2 - Listar todas as Contas                                  *
				*           3 - Buscar Conta por Numero                                 *
				*           4 - Atualizar Dados da Conta                                *
				*           5 - Apagar Conta                                            *
				*           6 - Sacar                                                   *
				*           7 - Depositar                                               *
				*           8 - Transferir valores entre Contas                         *
				*           9 - Sair                                                    *
				*                                                                       *
				*************************************************************************
		
				"""+Cores.TEXT_RESET);
			System.out.println(Cores.TEXT_BLUE_BRIGHT+"Entre com a opção desejada: "+Cores.TEXT_RESET);
			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e) {
				System.out.println(Cores.TEXT_RED+"\nDigite valores inteiros!");
				leia.nextLine();
				opcao=0;
			}
			
			
			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD+ "\nBanco do Brazil com Z - O Futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}
			
			switch(opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Criar Conta\n\n");
				
				System.out.println("Digite o Numero da Agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println("Digite o Tipo da Conta (1 - CC ou 2 - CP): ");
					tipo = leia.nextInt();
				}while (tipo < 1 && tipo > 2);
				
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				
				switch(tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo,limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = leia.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(),agencia, tipo, titular, saldo, aniversario));
					}
				}
				
				KeyPress();
				break;
			case 2: 
				System.out.println(Cores.TEXT_WHITE_BOLD+"Listar todas as Contas\n\n");
				contas.listarTodas();
				KeyPress();
				break;
			case 3: 
				System.out.println(Cores.TEXT_WHITE_BOLD+"Consultar dados da Conta - por número\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);
				
				KeyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				var buscaConta = contas.buscarNaCollection(numero);
				
				if (buscaConta != null) {
					
					tipo = buscaConta.getTipo();
					
					System.out.println("Digite o Número da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o Nome do Titulat: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = leia.nextFloat();
					
					switch (tipo) {
						case 1 ->{
							System.out.println("Digite o Limite de Crédito (R$): ");
							limite = leia.nextFloat();
							
							contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
							
						}
						case 2 ->{
							System.out.println("Digite o dia do Aniversário da Conta");
							aniversario = leia.nextInt();
							
							contas.atualizar(new ContaPoupanca(numero,agencia,tipo,titular,saldo,aniversario));
							
						}
						default -> {
							System.out.println("Tipo de conta inválido!");
						}
					}
				}else {
					System.out.println("A Conta não foi encontrada!");
				}
				
				
				KeyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Apagar a Conta\n\n");
				
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				contas.deletar(numero);
				
				KeyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Saque\n\n");
				
				System.out.println("Digite o Numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o Valor do Saque (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.sacar(numero, valor);
				
				KeyPress();
				break;
			case 7: 
				System.out.println(Cores.TEXT_WHITE_BOLD+"Depósito\n\n");
				
				System.out.println("Digite o Numero da Conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o Valor do Deposito (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.depositar(numero, valor);
				
				KeyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Transferência entre Contas\n\n");
				
				System.out.println("Digite o Numero da Conta de Origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o Numero da Conta de Destino: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.err.println("Digite o Valor da Transferência (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				KeyPress();
				break;
			default: 
				System.out.println(Cores.TEXT_WHITE_BOLD+"\nOpção Inválida!\n");
				
				KeyPress();
				break;
			}
		
		}
		
	}
	
	public static void KeyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
		}catch(IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}
	
	
	public static void sobre() {
		System.out.println(Cores.TEXT_YELLOW+"""
				
				*********************************************************
				Projeto Desenvolvido por:
				James Moura - james.moura@genstudents.org
				https://github.com/JamesHMoura
				*********************************************************
				""");
	}

}
