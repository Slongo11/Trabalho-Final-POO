package aplicacao;

import dados.*;
import telas.TelaCadastroTransporte;
import java.util.List;

public class ACMEAirDrones {
	private final ListaTransporte listaTransporte;
	public ACMEAirDrones(){
		listaTransporte = new ListaTransporte();
		new TelaCadastroTransporte(this);
	}

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
			//cadastro de carga viva
			// cadastro de carga inanimada
			if(codigo==2){
				info = informacoes.get(12);
				boolean perigosa = info.equals("perigosa");
				t = new TransporteCargaInanimada(codigoCarga,nomeCliente,descricao,peso,latitideOrigem,latitideDestino,longitudeOrigem,longitudeDestino,perigosa);
				if(listaTransporte.cadastraTransporte(t)) {
					return "Cadastrado com sucesso!";
				}
				return "Nada a ser cadastrado.";
			}
			if(codigo==3){
				info = informacoes.get(10);
				double min = Double.parseDouble(info);
				info = informacoes.get(11);
				double max = Double.parseDouble(info);
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
	public String mostraInfoTransporte(){
		return listaTransporte.toString();
	}
}
