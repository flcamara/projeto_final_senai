package AppViagem.AppViagem.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Pacote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @NotEmpty
    private String destino;

    @NotEmpty
    private String dias;

    @NotEmpty
    private String hotel;

    @NotEmpty
    private String valor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
