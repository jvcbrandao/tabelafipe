package com.joaobrandao.TabelaFipe;

import Model.DadosModelo;
import Model.DadosVeiculo;
import Model.Modelo;
import Model.Veiculo;
import Service.ConsumoApi;
import Service.ConverterDados;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class TabelaFipeApplication {

	public static void main(String[] args) {
		String json;
		String veiculoEscolhido;

		Scanner scanner = new Scanner(System.in);
		Menu textoInicial = new Menu();
		ConsumoApi consumoApi = new ConsumoApi();
		ConverterDados converterDados = new ConverterDados();
		SpringApplication.run(TabelaFipeApplication.class, args);

		// Menu inicial para escolher o tipo de veículo
		do {
			textoInicial.menuInicial();
			veiculoEscolhido = scanner.nextLine().toLowerCase();
		} while (!veiculoEscolhido.equals("carros") &&
				!veiculoEscolhido.equals("motos") &&
				!veiculoEscolhido.equals("caminhoes"));

		// Consumir dados das marcas de veículos
		String endereco = "https://parallelum.com.br/fipe/api/v1/" + veiculoEscolhido + "/marcas";
		json = consumoApi.ConsumirApi(veiculoEscolhido, endereco);
		var marcas = converterDados.obterLista(json, DadosVeiculo.class);

		marcas.stream().sorted(Comparator.comparing(DadosVeiculo::codigo))
				.forEach(System.out::println);

		// Escolher um modelo
		textoInicial.modeloCarro();
		var modeloEscolhido = scanner.nextLine();

		// Consumir dados de modelos de um veículo escolhido
		json = consumoApi.ConsumirModelo(veiculoEscolhido, modeloEscolhido);
		var dadosModelo = converterDados.obterDados(json, DadosModelo.class);

		dadosModelo.modelos().forEach(modelo -> System.out.println(modelo));

		// Filtrar modelos
		System.out.println("Informe o carro que deseja: ");
		var nomeBusca = scanner.nextLine();

		List<Modelo> modelosFiltrados = dadosModelo.modelos().stream()
				.filter(m -> m.nome().toLowerCase().contains(nomeBusca.toLowerCase()))
				.collect(Collectors.toList());

		System.out.println("Os modelos filtrados: " + modelosFiltrados);

		// Escolher o código do modelo
		System.out.println("Informe o código do modelo desejado: ");
		var codigoModelo = scanner.nextLine();

		// Consumir dados dos anos do modelo
		endereco = "https://parallelum.com.br/fipe/api/v1/" + veiculoEscolhido +
				"/marcas/" + modeloEscolhido + "/modelos/" + codigoModelo + "/anos";
		json = consumoApi.ConsumirApi(veiculoEscolhido, endereco);

		List<Modelo> anos = converterDados.obterLista(json, Modelo.class);

		List<Veiculo> veiculos = new ArrayList<>();

		// Consumir dados de veículos com base nos anos
		for (int i = 0; i < anos.size(); i++) {
			var enderecoAnos = endereco + "/" + anos.get(i).codigo();
			json = consumoApi.ConsumirApi(veiculoEscolhido, enderecoAnos);

			// Usar o método adequado para lidar com um JSON que pode ser uma lista ou objeto
			List<Veiculo> veiculo = converterDados.obterListaSeForObjetoOuLista(json, new TypeReference<List<Veiculo>>() {});
			veiculos.addAll(veiculo);  // Adiciona todos os veículos encontrados
		}

		// Exibir os veículos encontrados
		System.out.println("Os veículos encontrados são: ");
		veiculos.forEach(System.out::println);
	}
}
