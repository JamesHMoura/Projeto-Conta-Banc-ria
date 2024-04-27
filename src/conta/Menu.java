package conta;
import java.util.*;
import conta.util.*;
public class Menu {

	public static void main(String[] args) {
		Scanner leia = new Scanner(System.in);
		int opcao;
		
		
		while(true) {
			System.out.println(Cores.TEXT_BLUE_BRIGHT +"""
				*************************************************************************
				
							BANCO DO BRAZIL COM Z
				
				*************************************************************************
				
							1 - Criar Conta
							2 - Listar todas as Contas
							3 - Buscar Conta por Numero
							4 - Atualizar Dados da Conta
							5 - Apagar Conta
							6 - Sacar
							7 - Depositar
							8 - Transferir valores entre Contas
							9 - Sair
								
				*************************************************************************
				Entre com a opção desejada: 
				
				"""+Cores.TEXT_RESET);
		
			opcao = leia.nextInt();
			
			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD+ "\nBanco do Brazil com Z - O Futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}
			
			switch(opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Criar Conta\n\n");
				break;
			case 2: 
				System.out.println(Cores.TEXT_WHITE_BOLD+"Listar todas as Contas\n\n");
				break;
			case 3: 
				System.out.println(Cores.TEXT_WHITE_BOLD+"Consultar dados da Conta - por número\n\n");
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Atualizar dados da Conta\n\n");
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Apagar a Conta\n\n");
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Saque\n\n");
				break;
			case 7: 
				System.out.println(Cores.TEXT_WHITE_BOLD+"Depósito\n\n");
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD+"Transferência entre Contas\n\n");
				break;
			default: 
				System.out.println(Cores.TEXT_WHITE_BOLD+"\nOpção Inválida!\n");
				break;
			}
		
		}
		
	}
	public static void sobre() {
		System.out.println("""
				
				*********************************************************
				Projeto Desenvolvido por:
				James Moura - james.moura@genstudents.org
				https://github.com/JamesHMoura
				*********************************************************
				""");
	}

}
