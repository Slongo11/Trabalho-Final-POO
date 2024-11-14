package aplicacao;

import dados.*;
import telas.TelaCadastroTransporte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ACMEAirDrones {
	private final ListaTransporte listaTransporte;
	public ACMEAirDrones(){
		listaTransporte = new ListaTransporte();
		carregaConteudo("arquivos/salva.txt");
		new TelaCadastroTransporte(this);
	}

	/**
	 * <p>Le as informacoes do trasnporte validando e cadastrando</p>
	 * @param informacoes lista a ser lida
	 * @return informacoes do cadastro
	 */
	public String leInfoTransporte(List<String> informacoes){
		try {

			Transporte t;
			String info = informacoes.get(0);
			if(!info.matches("[0-9]*")){
				throw new Exception("Tipo de carga não informado.");
			}
			int codigo = Integer.parseInt(info);
			info = informacoes.get(1);
			int codigoCarga = Integer.parseInt(info);

			info = informacoes.get(2);
			if(info.isEmpty())
				throw new Exception("Nome do cliente não informado.");
			String nomeCliente = info;

			info = informacoes.get(3);
			if(info.isEmpty())
				throw new Exception("Descrição não informada.");
			String descricao = info;
			info = informacoes.get(4);
			double peso = Double.parseDouble(info);
			info = informacoes.get(5);
			double latitideOrigem = Double.parseDouble(info);
			info = informacoes.get(6);
			double latitideDestino = Double.parseDouble(info);
			info = informacoes.get(7);
			double longitudeOrigem = Double.parseDouble(info);
			info = informacoes.get(8);
			double longitudeDestino = Double.parseDouble(info);
			if(latitideOrigem >90 || latitideOrigem < -90)
				throw new Exception("Valor da latitude deve ser entre -90 e 90.");

			if(latitideDestino >90 || latitideDestino < -90)
				throw new Exception("Valor da latitude deve ser entre -90 e 90.");

			if(longitudeOrigem >180 || longitudeOrigem < -180)
				throw new Exception("Valor da longitude deve ser entre -180 e 180.");

			if(longitudeDestino >180 || longitudeDestino < -180)
				throw new Exception("Valor da longitude deve ser entre -180 e 180.");

			//cadastro de carga de pessoas
			if( codigo== 1){
				info = informacoes.get(9);
				int pessoas = Integer.parseInt(info);
				if(pessoas < 1)
					throw new NumberFormatException();
				t = new TransportePessoal(codigoCarga,nomeCliente,descricao,peso,latitideOrigem,latitideDestino,longitudeOrigem,longitudeDestino,pessoas);
				if(listaTransporte.cadastraTransporte(t))
					return "Cadastrado com sucesso!";

				return "Nada a ser cadastrado.";
			}

			// cadastro de carga inanimada
			if(codigo==2){
				boolean perigosa;
				if(informacoes.size() > 10){
					info = informacoes.get(12);
					perigosa = info.equals("perigosa");
				}else{
					info = informacoes.get(9);
					perigosa = info.equals("true") ;
				}
				t = new TransporteCargaInanimada(codigoCarga,nomeCliente,descricao,peso,latitideOrigem,latitideDestino,longitudeOrigem,longitudeDestino,perigosa);
				if(listaTransporte.cadastraTransporte(t)) {
					return "Cadastrado com sucesso!";
				}
				return "Nada a ser cadastrado.";
			}
			//cadastro de carga viva
			if(codigo==3){
				double min;
				double max;
				if(informacoes.size() > 11) {
					info = informacoes.get(10);
					min = Double.parseDouble(info);
					info = informacoes.get(11);
					max = Double.parseDouble(info);
				}else {
					info = informacoes.get(9);
					min = Double.parseDouble(info);
					info = informacoes.get(10);
					max = Double.parseDouble(info);
				}
				if(min>max)
					throw new Exception("Graus mínimos maiores que graus máximos.");
				t = new TransporteCargaViva(codigoCarga,nomeCliente,descricao,peso,latitideOrigem,latitideDestino,longitudeOrigem,longitudeDestino,min,max);
				if(listaTransporte.cadastraTransporte(t))
					return "Cadastrado com sucesso!";

				return "Nada a ser cadastrado.";
			}

		}catch (NumberFormatException e){
			return "Erro ao cadastrar em campo numérico.";
		}catch (Exception e){
			return e.getMessage();
		}

		return "Não foi possivel cadastrar o transporte código repetido";
	}

	/**
	 * <p>Mostra as informacoes da lista de transporte</p>
	 * @return toda lista do transportes cadastrados.
	 */
	public String mostraInfoTransporte(){
		return listaTransporte.toString();
	}

	/**
	 * <p>Armazena em um arquivo tipo CSV</p>
	 */
	public void armazenaConteudo(){
		String local = "arquivos/salva.txt";
		Path path = Paths.get(local);
		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset())))
		{
			writer.print(listaTransporte.getCsvFormat());
		}catch(Exception e){
			System.err.println(e);
			System.err.println("Erro ao escrever o arquivo");
		}

	}

	/**
	 * <p>Carrega o conteudo do arquivo salvo</p>
	 * @param local de carregamento
	 */
	private void carregaConteudo(String local){
		ArrayList<String> info = new ArrayList<>();
		Path path = Paths.get(local);
		try(BufferedReader reader = new BufferedReader(Files.newBufferedReader(path, Charset.defaultCharset()))){
			String linha = null;
			while ((linha = reader.readLine()) != null){
				Scanner sc = new Scanner(linha).useDelimiter(";");

				while(sc.hasNext()){
					info.add(sc.next());

				}
				leInfoTransporte(info);
				info.clear();
			}
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
		catch(Exception e){
			System.err.println(e);
			System.err.println("Erro ao cadastra informacao");
		}
	}

}
