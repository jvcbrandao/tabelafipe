
# Tabela Fipe Application

## Descrição
O Tabela Fipe Application é um projeto desenvolvido em Java com Spring Boot que permite consultar informações da Tabela FIPE de veículos, como carros, motos e caminhões, utilizando a API pública da FIPE.

Este projeto permite ao usuário:
- Escolher o tipo de veículo (carros, motos, caminhões);
- Filtrar marcas e modelos de veículos;
- Consultar informações detalhadas de veículos com base em ano e código.

## Funcionalidades
1. **Consulta de Marcas**: Exibe uma lista de marcas para o tipo de veículo escolhido.
2. **Filtragem de Modelos**: Permite filtrar modelos por nome.
3. **Consulta de Detalhes**: Exibe informações detalhadas de veículos com base no ano e código.

## Tecnologias Utilizadas
- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para facilitar o desenvolvimento da aplicação.
- **Jackson**: Biblioteca para manipulação de JSON.
- **HTTP Client**: Utilizado para consumir a API da FIPE.
- **API FIPE**: Fonte de dados da aplicação.

## Como Executar
1. Clone este repositório:
   ```bash
   git clone https://github.com/seuusuario/tabelafipe.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd tabelafipe
   ```
3. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Interaja com o terminal para realizar consultas.

## Estrutura do Projeto
- **`TabelaFipeApplication`**: Classe principal que inicializa a aplicação e gerencia o fluxo de execução.
- **`Service`**:
  - `ConsumoApi`: Responsável por realizar as chamadas à API FIPE.
  - `ConverterDados`: Realiza a conversão de dados JSON para objetos Java.
- **`Model`**:
  - `DadosModelo`, `DadosVeiculo`, `Modelo`, `Veiculo`: Representam as entidades manipuladas na aplicação.

## Exemplos de Uso
### Escolha do tipo de veículo
- Entrada: `carros`
- Saída: Lista de marcas de carros disponíveis.

### Consulta de modelo
- Entrada: Código da marca e nome do modelo.
- Saída: Modelos correspondentes filtrados.

## Melhorias Futuras
- Adicionar uma interface gráfica para facilitar a interação.
- Implementar testes unitários para maior cobertura.
- Adicionar suporte para idiomas além do português.

## Autor
Desenvolvido por João Brandão.

## Licença
Este projeto está licenciado sob a [MIT License](LICENSE).
