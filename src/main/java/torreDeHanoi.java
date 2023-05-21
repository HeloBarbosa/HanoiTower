//Trabalho Torre de Hanoi - Grupo: Heloisa Barbosa e Andressa Darzé

package Trabalho1;

import java.util.*;


public class torreDeHanoi
{
	public static int[][] criarMatrix(int numeroDiscos) // Método cria uma matriz usada para construir o print do
														// desenho
	{
		int[][] Matrix = new int[numeroDiscos][3];
		for (int linha = 0; linha < Matrix.length; linha++)
		{
			Matrix[linha][0] = 1 + linha; //O +1 é para que o primeiro elemento da primeira coluna seja diferente de zero
		} //Numeros diferentes de zero representam os discos.
		return Matrix;
	}

	public static int[][] movement(int[][] Matrix, int posicaoInicial, int posicaoFinal) // Método para os movimentos
	{
		// movementValue: 1(A->B) 2(A->C) 3(B->A) 4(B->C) 5(C->A) 6(C->B)
		int value = 0;
		for (int linha = 0; linha < Matrix.length; linha++)
		{
			if (Matrix[linha][posicaoInicial] != 0)
			{
				value = Matrix[linha][posicaoInicial];
				Matrix[linha][posicaoInicial] = 0;
				break;
			}
		}
		if (Matrix[Matrix.length - 1][posicaoFinal] == 0)
		{
			Matrix[Matrix.length - 1][posicaoFinal] = value;
		}
		else
		{
			for (int linha = 0; linha < Matrix.length; linha++)
			{
				if (Matrix[linha][posicaoFinal] != 0)
				{
					Matrix[linha - 1][posicaoFinal] = value;
					break;
				}
			}
		}
		return Matrix;
	}

	public static boolean verificarMovimento(int[][] Matrix, int posicaoInicial, int posicaoFinal)
	{
		// movementValue: 1(A->B) 2(A->C) 3(B->A) 4(B->C) 5(C->A) 6(C->B)   
		//A=0 ,B=1 e C=2 => Ex Movimento 1 (1,2)= (A->B)
		int value = 0;
		for (int linha = 0; linha < Matrix.length; linha++)
		{
			if (Matrix[linha][posicaoInicial] != 0) //Pego o primeiro elemento diferente de zero da posicao inicial
			{
				value = Matrix[linha][posicaoInicial];
				break; //Só quero o primeiro elemento
			}
		}
		if (Matrix[Matrix.length - 1][posicaoFinal] == 0)
		{
			return true; //Ver se a posição final estiver vazia, se estiver vazia o movimento é possível
		}
		else
		{
			for (int linha = 0; linha < Matrix.length; linha++)
			{
				if (Matrix[linha][posicaoFinal] != 0)
				{
					if (Matrix[linha][posicaoFinal] < value) //Compara se as peças são maiores ou menores
					{
						return false; 
					}
					else
					{
						return true;
					}
				}
			}
		}
		return true;
	}

	public static boolean verificarPeca(int[][] Matrix, int posicaoInicial)
	{
		for (int linha = 0; linha < Matrix.length; linha++)
		{
			if (Matrix[linha][posicaoInicial] != 0) //Não é possível remover uma peça de um pino vazio
			{
				return true;
			}
		}
		return false;
	}

	public static boolean verificarVitoria(int[][] Matrix)
	{
		for (int linha = 0; linha < Matrix.length; linha++)
		{
			if (Matrix[linha][2] == 0)
			{
				return false;
			}
		}
		return true;
	}

	public static String[][] criarTabuleiro(int numeroDiscos) // Vai ser usado pro desenho
	{
		int numeroColunas = (6 * (numeroDiscos - 1) + 13); // Tamanho do maior disco é dado por (3+2(n-1))
		// então,
		// quero 3 pinos
		// na posicao do meio do disco considerando um espaçamento de 1 entre cada pino e de 2 correspondentes a
		// borda, logo : 3*(3+2(n-1))+4
		int numeroLinhas = (numeroDiscos + 3);
		
		String[][] gameBoard = new String[numeroLinhas][numeroColunas];

		return gameBoard;
	}

	public static String[][] matrixToTabuleiro(int[][] Matrix, String[][] gameBoard, int numeroDiscos)
	{
		int numeroLinhas = (numeroDiscos + 3);
		int pino1Posicao = 1 + (((3 + 2 * (numeroDiscos - 1)) - 1)) / 2;
		int pino2Posicao = 3 * pino1Posicao;
		int pino3Posicao = 5 * pino1Posicao;
		int tamanhoPeca = 0;
		int tamanhoPecaParaCadaLado = 0;

		for (int linha = 0; linha < gameBoard.length; linha++) // Criar o tabuleiro vazio
		{
			for (int coluna = 0; coluna < gameBoard[linha].length; coluna++)
			{
				if (linha == numeroLinhas - 2) // Desenhar mesa
					gameBoard[linha][coluna] = "=";

				else if (linha != numeroLinhas - 1 && coluna == pino1Posicao
						|| linha != numeroLinhas - 1 && coluna == pino2Posicao
						|| linha != numeroLinhas - 1 && coluna == pino3Posicao)
					gameBoard[linha][coluna] = "|";
				else if (linha == numeroLinhas - 1 && coluna == pino1Posicao)
					gameBoard[linha][coluna] = "A";
				else if (linha == numeroLinhas - 1 && coluna == pino2Posicao)
					gameBoard[linha][coluna] = "B";
				else if (linha == numeroLinhas - 1 && coluna == pino3Posicao)
					gameBoard[linha][coluna] = "C";
				else
					gameBoard[linha][coluna] = " ";
			}
		}

		for (int linha = 0; linha < Matrix.length; linha++) // Criar as pecas com base na matriz (Matrix) do tabuleiro
		{
			if (Matrix[linha][0] != 0)
			{
				tamanhoPeca = 3 + 2 * (Matrix[linha][0] - 1); //Obtendo o numero de * para cada disco
				tamanhoPecaParaCadaLado = (tamanhoPeca + 1) / 2 - 1; //Quantidade de * de cada lado desconsiderando o meio
				for (int tamanho = -tamanhoPecaParaCadaLado; tamanho < tamanhoPecaParaCadaLado + 1; tamanho++)
				{
					gameBoard[linha + 1][pino1Posicao + tamanho] = "*";
				}
			}
			if (Matrix[linha][1] != 0)
			{
				tamanhoPeca = 3 + 2 * (Matrix[linha][1] - 1);
				tamanhoPecaParaCadaLado = (tamanhoPeca + 1) / 2 - 1;
				for (int tamanho = -tamanhoPecaParaCadaLado; tamanho < tamanhoPecaParaCadaLado + 1; tamanho++)
				{
					gameBoard[linha + 1][pino2Posicao + tamanho] = "*";
				}
			}
			if (Matrix[linha][2] != 0)
			{
				tamanhoPeca = 3 + 2 * (Matrix[linha][2] - 1);
				tamanhoPecaParaCadaLado = (tamanhoPeca + 1) / 2 - 1;
				for (int tamanho = -tamanhoPecaParaCadaLado; tamanho < tamanhoPecaParaCadaLado + 1; tamanho++)
				{
					gameBoard[linha + 1][pino3Posicao + tamanho] = "*";
				}
			}
		}
		return gameBoard;
	}

	public static void printMatrix(int[][] Matrix)
	{
		for (int linha = 0; linha < Matrix.length; linha++)
		{
			for (int coluna = 0; coluna < Matrix[linha].length; coluna++)
			{
				System.out.print(Matrix[linha][coluna]);
			}
			System.out.println();
		}
	}

	public static void printTabuleiro(String[][] gameBoard)
	{
		for (int linha = 0; linha < gameBoard.length; linha++)
		{
			for (int coluna = 0; coluna < gameBoard[linha].length; coluna++)
			{
				System.out.print(gameBoard[linha][coluna]);
			}
			System.out.println();
		}
	}

	public static void main(String args[])
	{
		int numeroDiscos;
		boolean endGame2 = false;
		boolean endGame = false;

		System.out.println("// ------------------------ Torre de Hanoi ------------------------ \\\\");

		while (endGame == false)
		{
			Scanner scan = new Scanner(System.in);

			System.out.println();
			System.out.println("Por favor, insira o número de disos:               Y/y: Sair do jogo ");

			if (scan.hasNextInt())
			{
				numeroDiscos = scan.nextInt();
				scan.nextLine();

				if (numeroDiscos >= 3 && numeroDiscos <= 10)
				{
					int numeroOtimo = (int) Math.pow(2, numeroDiscos) - 1;

					int seuMovimento = 0;

					System.out.println(
							"O número ótimo de jogadas para " + numeroDiscos + " discos, é igual a " + numeroOtimo);
					System.out.println("-------------------------------------------------------------------");
					System.out.println();
					System.out.println("Seus movimentos: " + seuMovimento);
					System.out.println();

					int[][] Matrix = criarMatrix(numeroDiscos);
					String[][] gameBoard = criarTabuleiro(numeroDiscos);

					gameBoard = matrixToTabuleiro(Matrix, gameBoard, numeroDiscos);
					printTabuleiro(gameBoard);

					while (endGame2 == false)
					{
						int movimentoEscolhido;
						System.out.println();
						System.out.println("Por favor, escolha o seu movimento: ");
						System.out.println(
								"1: A->B   2: A->C   3: B->A   4: B->C   5: C->A   6: C->B	 Y/y: Sair do jogo");

						if(scan.hasNextInt())
						{	
							movimentoEscolhido = scan.nextInt();
							scan.nextLine();

							seuMovimento = seuMovimento + 1;

							if (movimentoEscolhido == 1 && verificarMovimento(Matrix, 0, 1) == true)
							{ // A->B
								if (verificarPeca(Matrix, 0) == false)
								{
									seuMovimento = seuMovimento - 1;
									System.out.println("O movimento só é possível quando há peça para ser retirada.");

								}
								else
								{
									Matrix = movement(Matrix, 0, 1);
								}
							}
							else if (movimentoEscolhido == 2 && verificarMovimento(Matrix, 0, 2) == true)
							{ // A->C
								if (verificarPeca(Matrix, 0) == false)
								{
									seuMovimento = seuMovimento - 1;
									System.out.println("O movimento só é possível quando há peça para ser retirada.");
								}
								else
								{
									Matrix = movement(Matrix, 0, 2);
								}
							}
							else if (movimentoEscolhido == 3 && verificarMovimento(Matrix, 1, 0) == true)
							{ // B->A
								if (verificarPeca(Matrix, 1) == false)
								{
									seuMovimento = seuMovimento - 1;
									System.out.println("O movimento só é possível quando há peça para ser retirada.");
								}
								else
								{
									Matrix = movement(Matrix, 1, 0);
								}
							}
							else if (movimentoEscolhido == 4 && verificarMovimento(Matrix, 1, 2) == true)
							{ // B->C
								if (verificarPeca(Matrix, 1) == false)
								{
									seuMovimento = seuMovimento - 1;
									System.out.println("O movimento só é possível quando há peça para ser retirada.");
								}
								else
								{
									Matrix = movement(Matrix, 1, 2);
								}
							}
							else if (movimentoEscolhido == 5 && verificarMovimento(Matrix, 2, 0) == true)
							{ // C->A
								if (verificarPeca(Matrix, 2) == false)
								{
									seuMovimento = seuMovimento - 1;
									System.out.println("O movimento só é possível quando há peça para ser retirada.");
								}
								else
								{
									Matrix = movement(Matrix, 2, 0);
								}
							}
							else if (movimentoEscolhido == 6 && verificarMovimento(Matrix, 2, 1) == true)
							{ // C->B
								if (verificarPeca(Matrix, 2) == false)
								{
									seuMovimento = seuMovimento - 1;
									System.out.println("O movimento só é possível quando há peça para ser retirada.");
								}
								else
								{
									Matrix = movement(Matrix, 2, 1);
								}
							}
							else if (movimentoEscolhido > 6 || movimentoEscolhido < 1)
							{
								System.out.println("Sua escolha deve estar nas opções apresentadas!");
								seuMovimento = seuMovimento - 1;
							}
							else
							{
								System.out.println(
										"Não é possível colocar uma peça de tamanho maior em cima de uma peça de tamanho menor!");
								System.out.println();
								seuMovimento = seuMovimento - 1;
							}
							System.out.println("Seus movimentos: " + seuMovimento);
							System.out.println();
							gameBoard = matrixToTabuleiro(Matrix, gameBoard, numeroDiscos);
							printTabuleiro(gameBoard);
							
							if (verificarVitoria(Matrix)==true) {
								System.out.println("*********************************************************");
								System.out.println(" Parabéns! Você ganhou com "+seuMovimento+" jogadas!!! ");
								endGame = true;
								endGame2 = true;
								if(numeroOtimo==seuMovimento) 
								{
									System.out.println("Você ganhou realizando o número mínimo de jogadas, que é "+numeroOtimo);
								}
								else {
									System.out.println("No entanto você poderia ter ganho com o número mínimo de jogadas, que é "+numeroOtimo);
								}
								System.out.println("*********************************************************");
							}

						}
						else
						{
							String letra = scan.nextLine();
							
							
							if (letra.equalsIgnoreCase("Y"))
							{	
								endGame2 = true;
								endGame = true;
								System.out.println();
								System.out.println("// ================== Você saiu do jogo! ================== //");
							}
							else
							{	
								System.out.println("Opção inválida! Tente novamente!");
								endGame2 = false;
							}
						}
					}
				}
				else if (numeroDiscos < 3)
				{
					System.out.println("Deve haver no mínimo 3 discos.");
				}
				else
				{
					System.out.println("Só pode conter no máximo 10 discos.");
				}
			}
			else 
			{
				String letra = scan.nextLine();

				if (letra.equalsIgnoreCase("Y"))
				{
					endGame2 = true;
					endGame = true;
					System.out.println();
					System.out.println("// ================== Você saiu do jogo! ================== //");
				}
				else
				{
					System.out.println("Opção inválida! Tente novamente!");
					endGame2 = false;
				}
			}
		}
	}
}
