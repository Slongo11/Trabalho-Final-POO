package aplicacao;

import dados.*;
import telas.TelaPrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ACMEAirDrones {
	private final ListaTransporte listaTransporte;
	private final Frota frota;
	public ACMEAirDrones(){
		listaTransporte = new ListaTransporte();
		frota = new Frota();

		new TelaPrincipal(this);
	}

	/**
	 * <p>Executa a aplicacao</p>
	 */
	public void executar(){
		carregaConteudo("arquivos/salva.csv");
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
//  TODO IMPLMENTAR
	public boolean cadastrarDrone(Drone d){
		return frota.adicionarDrone(d);
	}
	/**
	 * <p>Mostra as informacoes da lista de transporte</p>
	 * @return toda lista dos drones cadastrados cadastrados.
	 */
	public String mostraInfoDroneDrone(){
		return frota.listarDrones();
	}

	/**
	 * <p>Mostra as informacoes da lista de transporte</p>
	 * @return toda lista do transportes cadastrados.
	 */
	public String mostraInfoTransporte(){
		return listaTransporte.toString();
	}

	/**
	 * <p>Chama a funcao para achar o transporte</p>
	 * @param numero a ser procurado
	 * @return o Transporte achado com aquele numero ou null caso não ache
	 *
	 */
	public Transporte buscaTransporte(int numero){
		return listaTransporte.buscaPorNumero(numero);
	}

	/**
	 * <p>Busca o drone de com os parametros especifidoas da carga </p>
	 * @param carga tipo a ser buscado
	 * @param distancia percorida pelo drone
	 * @param pessoas caso exista a quantidade
	 * @return o Drone
	 * @throws Exception por por informacoes incorretas
	 */
	public Drone buscaDrone(CategoriaCarga carga,double distancia, int pessoas) throws Exception{
		return carga == CategoriaCarga.PESSOAS ? frota.capacitado(carga, distancia, pessoas): frota.capacitado(carga, distancia);
	}

	/**
	 * <p>Altera a situacao de um unico Transporte </p>
	 * @param t o transporte a ser alterado
	 * @return a situacao bem sucedida
	 * @throws Exception informa
	 */
	public String alteraSituacaoTrasporte(Transporte t, Estado atulizar) throws Exception{
		Estado estado = t.getSituacao();
		if(estado == Estado.PENDENTE && atulizar != Estado.PENDENTE){
			if(atulizar == Estado.ALOCADO){
				Drone d;
				if(t instanceof TransportePessoal) {
					d = buscaDrone(CategoriaCarga.PESSOAS, t.calculaKm(),((TransportePessoal)t).getQtdPessoas());
				}else if(t instanceof TransporteCargaInanimada) {
					d = buscaDrone(CategoriaCarga.CARGA_INANIMADA, t.calculaKm(), 0);
				}else{
					d = buscaDrone(CategoriaCarga.CARGA_VIVA, t.calculaKm(), 0);
				}
				if(d == null)
					throw new Exception("Nenhum drone encontrado.");
				t.adicionaDrone(d);
			} else{
				t.atualizaStatus(atulizar);
			}
			return "Status atualizado para: " + atulizar.toString();
		}
		if(estado == Estado.ALOCADO && atulizar != Estado.ALOCADO){
			t.atualizaStatus(atulizar);
			return"Atualizado com sucesso para: " + atulizar.toString();
		}
		throw new Exception("Não pode ser alterado com este situacao ["+ atulizar.toString() + "] logo que é " + estado.toString());
	}

	/**
	 * <p>Acha algum drone a ser alocado aos transporte</p>
	 * @throws Exception joga uma excecao para lista de transporte vazia
	 */
	public void processaTransportesPendentes() throws Exception{
		Queue<Transporte> pendente =  listaTransporte.getFilaDeTransporte();
		if(pendente.isEmpty()){
			throw new Exception("Não existe nenhum transporte pendente");
		}
		Queue<Transporte> novaQueue = new ArrayDeque();
		List<Drone> listaDrone = frota.getDrones();

		while(!pendente.isEmpty()){
			Transporte transporte = pendente.remove();
			double distancia = transporte.calculaKm();

			for(Drone d : listaDrone){
				if(d.getAutonomia() >= distancia){
					transporte.adicionaDrone(d);
					break;
				}
			}
			if(transporte.getSituacao() == Estado.PENDENTE){
				novaQueue.add(transporte);
			}

		}
		pendente.addAll(novaQueue);
	}

	public void leInfoDrone(ArrayList<String> informacoes){
		//TODO TERMINAR ESSE METODO
	}

	/**
	 * <p>Armazena em um arquivo tipo CSV</p>
	 */
	public void armazenaConteudo(){
		String local = "arquivos/salva.csv";
		Path path = Paths.get(local);
		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset())))
		{
			writer.print(listaTransporte.getCsvFormat());
			writer.println("-1\n" + frota.getCsvFormat());
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
		boolean verifica = false;
		try(BufferedReader reader = new BufferedReader(Files.newBufferedReader(path, Charset.defaultCharset()))){
			String linha = null;
			while ((linha = reader.readLine()) != null){
				Scanner sc = new Scanner(linha).useDelimiter(";");

				while(sc.hasNext()){
					info.add(sc.next());
				}
				if(info.getFirst().equals("-1")){
					verifica = true;
				}

				if(!verifica) {
					leInfoTransporte(info);
				}else {
					leInfoDrone(info);
				}
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
