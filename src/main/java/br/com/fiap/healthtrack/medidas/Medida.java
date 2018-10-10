package br.com.fiap.healthtrack.medidas;

import java.util.Date;


/**
 * @author Sid
 * Superclasse para todas as medidas do sistema
 *
 */
public class Medida {

	private int id;
	private Date date;
	String _id;


	/**
	 * A data que a medida foi adicionada ao sistema
	 * @return date 
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Altera a data da medida
	 * @param date
	 */
	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medida other = (Medida) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	
}
