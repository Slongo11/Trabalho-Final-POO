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
	private String leInfoTransporte(List<String> informacoes) throws Exception{

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
			double longitudeOrigem = Double.parseDouble(info);

			info = informacoes.get(7);
			double latitideDestino = Double.parseDouble(info);

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
				if(listaTransporte.cadastraTransporte(t)) {
					if(informacoes.size()>10){
						String numD = informacoes.get(10);
						info = informacoes.get(11);

						Estado estado;
						Drone d = null;
						if(!numD.equals("null")){
							d = frota.buscaDrone(Integer.parseInt(numD));
						}
						if (Estado.PENDENTE.toString().equals(info)) {
							estado = Estado.PENDENTE;
						} else if (Estado.ALOCADO.toString().equals(info)) {
							estado = Estado.ALOCADO;
						} else if (Estado.TERMINADO.toString().equals(info)) {
							estado = Estado.TERMINADO;
						} else {
							estado = Estado.CANCELADO;
						}
						alteraSituacaoTrasporte(t, estado, d);
					}

					return "Cadastrado com sucesso!";
				}
				return "Nada a ser cadastrado.";
			}

			// cadastro de carga inanimada
			if(codigo==2){
				boolean perigosa = false;
				info = informacoes.get(9);
				if(info.equals("perigosa") || info.equals("true")){
					perigosa = true;
				}
				t = new TransporteCargaInanimada(codigoCarga,nomeCliente,descricao,peso,latitideOrigem,latitideDestino,longitudeOrigem,longitudeDestino,perigosa);
				if(listaTransporte.cadastraTransporte(t)) {
					if(informacoes.size() > 10) {
						String numD = informacoes.get(10);
						info = informacoes.get(11);

						Estado estado;
						Drone d = null;
						if(!numD.equals("null")){
							d = frota.buscaDrone(Integer.parseInt(numD));
						}
						if (Estado.PENDENTE.toString().equals(info)) {
							estado = Estado.PENDENTE;
						} else if (Estado.ALOCADO.toString().equals(info)) {
							estado = Estado.ALOCADO;
						} else if (Estado.TERMINADO.toString().equals(info)) {
							estado = Estado.TERMINADO;
						} else {
							estado = Estado.CANCELADO;
						}
						alteraSituacaoTrasporte(t, estado, d);
					}

					return "Cadastrado com sucesso!";
				}
				return "Nada a ser cadastrado.";
			}
			//cadastro de carga viva
			if(codigo==3){
				double min;
				double max;

				info = informacoes.get(9);
				min = Double.parseDouble(info);
				info = informacoes.get(10);
				max = Double.parseDouble(info);

				if(min>max)
					throw new Exception("Graus mínimos maiores que graus máximos.");
				t = new TransporteCargaViva(codigoCarga,nomeCliente,descricao,peso,latitideOrigem,latitideDestino,longitudeOrigem,longitudeDestino,min,max);
				if(listaTransporte.cadastraTransporte(t)){
						if(informacoes.size()>11){
						String numD = informacoes.get(11);
						info = informacoes.get(12);
						Estado estado;
						Drone d = null;
						if(!numD.equals("null")){
							d = frota.buscaDrone(Integer.parseInt(numD));
						}
						if(Estado.PENDENTE.toString().equals(info)) {
							estado = Estado.PENDENTE;
						}else if(Estado.ALOCADO.toString().equals(info)){
							estado = Estado.ALOCADO;
						}else if(Estado.TERMINADO.toString().equals(info)){
							estado = Estado.TERMINADO;
						}else{
							estado = Estado.CANCELADO;
						}
						alteraSituacaoTrasporte(t,estado,d);
					}
					return "Cadastrado com sucesso!";
				}


				return "Nada a ser cadastrado.";
			}


		return "Não foi possivel cadastrar o transporte código repetido";
	}

	/**
	 * <p>Cadastrar os drones solicitados</p>
	 * @param d drone a ser cadastradao
	 * @return verdadeiro quando for cadastrado corretamente ou falso caso contrário
	 */
	public boolean cadastrarDrone(Drone d){
		return frota.adicionarDrone(d);
	}

	/**
	 * <p>Cadastra os transportes solicitados</p>
	 * @param t transporte a ser cadastrado
	 * @return verdadeiro quando for cadastrado corretamente ou falso caso contrário
	 */
	public boolean cadastrarTransporte(Transporte t){
		return listaTransporte.cadastraTransporte(t);
	}
	/**
	 * <p>Mostra as informacoes da lista de Drones</p>
	 * @return toda lista dos drones cadastrados cadastrados.
	 */
	public String mostraInfoDrone(){
		return frota.toString();
	}

	/**
	 * <p>Mostra todos os drones pessoal</p>
	 * @return informacoes dos drones
	 */
	public String mostraInfoDronePessoal(){
		return frota.listaDronePessoal();
	}
	/**
	 * <p>Mostra todos os drones de carga</p>
	 * @return informacoes dos drones
	 */
	public String mostraInfoDroneCarga(){
		return frota.listaDronesCarga();
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
		return frota.capacitado(carga, distancia, pessoas);
	}

	public Drone buscaDrone(CategoriaCarga carga,double distancia) throws Exception{
		return frota.capacitado(carga, distancia);
	}

	/**
	 * <p>Altera a situacao de um unico Transporte </p>
	 * @param t o transporte a ser alterado
	 * @return a situacao bem sucedida
	 * @throws Exception informa
	 */
	public String alteraSituacaoTrasporte(Transporte t, Estado atulizar) throws Exception{
		if(t == null){
			throw new Exception("Transporte não encontrado");
		}
		Estado estado = t.getSituacao();
		if(estado == Estado.PENDENTE && atulizar != Estado.PENDENTE && atulizar != Estado.TERMINADO){
			if(atulizar == Estado.ALOCADO){
				Drone d;
				if(t instanceof TransportePessoal) {
					d = buscaDrone(CategoriaCarga.PESSOAS, t.calculaKm(),((TransportePessoal)t).getQtdPessoas());
				}else if(t instanceof TransporteCargaInanimada) {
					d = buscaDrone(CategoriaCarga.CARGA_INANIMADA, t.calculaKm());
				}else{
					d = buscaDrone(CategoriaCarga.CARGA_VIVA, t.calculaKm());
				}
				if(d == null)
					return "Nenhum drone encontrado.";
				t.adicionaDrone(d);
				d.addTransporte(t);
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
	 * <p>Adicona os drones os transportes que antes possuiam e o ou o status</p>
	 * @param t transporte a ser adicionado o drone
	 * @param atulizar qual estado precisa ser adicionado
	 * @param d o drone ou null caso nao tenha
	 */
	private void alteraSituacaoTrasporte(Transporte t, Estado atulizar,Drone d){
		if(t != null){
			t.atualizaStatus(atulizar);
			if(d != null){
				t.setDrone(d);
				d.addTransporte(t);
			}
		}
	}

	/**
	 * <p>Valida se tem algum transporte pendente</p>
	 * @return
	 */
	private Queue<Transporte> validaPendencias(Queue<Transporte> pendencias){
		Queue<Transporte> retorno = new LinkedList<>();
		while(!pendencias.isEmpty()){
			Transporte t = pendencias.poll();
			if(t.getSituacao() == Estado.PENDENTE){
				retorno.add(t);
			}
		}
		return retorno;
	}

	/**
	 * <p>Acha algum drone a ser alocado aos transporte</p>
	 * @throws Exception joga uma excecao para lista de transporte vazia
	 */
	public void processaTransportesPendentes() throws Exception{
		Queue<Transporte> pendente =  listaTransporte.getFilaDeTransporte();
		pendente = validaPendencias(pendente);
		if(pendente.isEmpty()){
			throw new Exception("Não existe nenhum transporte pendente");
		}
		Queue<Transporte> novaQueue = new LinkedList();

		while(!pendente.isEmpty()){
			Transporte transporte = pendente.remove();

			alteraSituacaoTrasporte(transporte,Estado.ALOCADO);

			if(transporte.getSituacao() == Estado.PENDENTE){
				novaQueue.add(transporte);
			}
		}

		pendente.addAll(novaQueue);
	}

	/**
	 * <p>Faz a leitura dos drones a serem cadastarados</p>
	 * @param informacoes informacoes de cadastro
	 */
	private void leInfoDrone(List<String> informacoes){
		//1-Pessoal, 2-Carga inanimada, 3-Carga viva
		Drone d = null;
		String info = informacoes.get(0);
		int tipo = Integer.parseInt(info);
		info = informacoes.get(1);
		int codigo = Integer.parseInt(info);
		info = informacoes.get(2);
		double custo = Double.parseDouble(info);
		info = informacoes.get(3);
		double autonomia = Double.parseDouble(info);
		if(tipo == 1){

			info = informacoes.get(4);
			int qtdPessoas = Integer.parseInt(info);
			d = new DronePessoal(codigo, custo, autonomia, qtdPessoas);

		}else if(tipo == 2){

			info = informacoes.get(4);
			double peso = Double.parseDouble(info);
			info = informacoes.get(5);
			boolean protecao = info.equals("true");
			d = new DroneCargaInanimada(codigo, custo, autonomia, peso, protecao);

		}else if(tipo == 3){

			info = informacoes.get(4);
			double peso = Double.parseDouble(info);
			info = informacoes.get(5);
			boolean protecao = info.equals("true");
			d = new DroneCargaViva(codigo,custo, autonomia, peso, protecao);

		}
		cadastrarDrone(d);

	}

	/**
	 * <p>Chamada para os arquivos simula</p>
	 */
	public void simulaCarrega(){
		String arquvoDorne = "arquivos/SIMULA-DRONES.csv";
		String arquvoTransporte = "arquivos/SIMULA-TRANSPORTES.csv";
		simulaCarrega(arquvoDorne);
		simulaCarrega(arquvoTransporte);
	}

	/**
	 * <p>Executa o simula lendo os arquivos </p>
	 * @param local a ser lido os arquivos
	 */
	private void simulaCarrega(String local){
		Path path = Paths.get(local);
		try(BufferedReader reader = new BufferedReader(Files.newBufferedReader(path, Charset.defaultCharset()))){
			String linha = null;
			List<String> info = new ArrayList<>();
			while ((linha = reader.readLine()) != null){
				Scanner sc = new Scanner(linha).useDelimiter(";");

				while(sc.hasNext()){
					info.add(sc.next());
				}
				if(local.equalsIgnoreCase("arquivos/SIMULA-DRONES.csv")){
					leInfoDrone(info);
				}else{
					leInfoTransporte(info);
				}
				info.clear();


			}
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println("Erro ao cadastra informacao");
		}
	}

	/**
	 * <p>Armazena em um arquivo tipo CSV</p>
	 * @param local a ser armazenado
	 */
	public void armazenaConteudo(String local){
		String local1 = "arquivos/"+local+".csv";
		Path path = Paths.get(local1);
		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset())))
		{
			writer.print( frota.getCsvFormat());
			writer.println("-1\n" +listaTransporte.getCsvFormat());
		}catch(Exception e){
			System.err.println(e);
			System.err.println("Erro ao escrever o arquivo");
		}

	}

	/**
	 * <p>Carrega o conteudo do arquivo salvo</p>
	 * @param local de carregamento
	 * @return caso bem sucedido verdadeiro ou falso quando não encotra o arquivo
	 */
	public boolean carregaConteudo(String local){
		List<String> info = new ArrayList<>();
		Path path = Paths.get(local);
		boolean verifica = false;
		try(BufferedReader reader = new BufferedReader(Files.newBufferedReader(path, Charset.defaultCharset()))){
			String linha = null;
			while ((linha = reader.readLine()) != null){
				Scanner sc = new Scanner(linha).useDelimiter(";");

				while(sc.hasNext()){
					info.add(sc.next());
				}
				if(!info.isEmpty()){
					if( info.getFirst().equals("-1")){
						verifica = true;
					}
					if(!verifica) {
						leInfoDrone(info);
					}else{
						if(!info.getFirst().equals("-1"))
							leInfoTransporte(info);
					}
						info.clear();
				}
			}
		}catch (IOException e){
			System.out.println(e.getMessage());
			return false;
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			System.err.println("Erro ao cadastra informacao");
			return false;
		}
		return true;
	}

}
