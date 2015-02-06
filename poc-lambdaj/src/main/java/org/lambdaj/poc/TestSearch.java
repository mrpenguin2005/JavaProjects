package org.lambdaj.poc;

public class TestSearch {

	public static void main(String[] args) {
		int[] vetor = { 31, 34, 38, 44, 6, 9, 10, 13, 14, 19, 22, 24, 28 };

		System.out.println("Resultado : " + menorNumero(vetor));
	}

	static int menorValorParcial(int[] vetor, int ini, int fim) {
		if (vetor[ini] < vetor[fim])
			return vetor[ini];
		else
			return vetor[fim];
	}

	static int menorNumero(int[] vetor, int ini, int fim) {
		int m = (fim - ini) / 2;
		if (menorValorParcial(vetor, ini, m) < menorValorParcial(vetor, m + 1, fim)) {
			if ((m - ini) <= 3) {
				return search_least(vetor);
			}
			return menorNumero(vetor, ini, m);
		}
		if (menorValorParcial(vetor, ini, m) > menorValorParcial(vetor, m + 1, fim)) {
			if ((fim - m) <= 3) {
				return search_least(vetor);
			}
			return menorNumero(vetor, m, fim);
		}
		return 0;
	}

	static int menorNumero(int[] vetor) {
		return menorNumero(vetor, 0, vetor.length - 1);
	}

	static int search_least(int[] vetor) {
		int j = -1;
		for (int i = 1; i < vetor.length; i++) {
			if (vetor[i] < vetor[i - 1]) {
				j = vetor[i];
			}
		}
		return j;
	}

}
