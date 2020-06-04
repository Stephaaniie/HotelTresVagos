package ar.com.ada.hoteltresvagos.Validaciones;

import ar.com.ada.hoteltresvagos.Interfaz.IngresoValido;

public class DniValidator implements IngresoValido{

    private String dni;

    public DniValidator(String dni) {
        this.dni = dni;
    }

    @Override	
	public boolean esIngresoValido() {
		return (soloNumeros());
	}

	private boolean soloNumeros() {
		int i, j = 0;
		String numero = "";
		String miDNI = ""; 
		String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};

		for(i = 0; i < this.dni.length(); i++) {
			numero = this.dni.substring(i, i+1);
			for(j = 0; j < unoNueve.length; j++) {
				if(numero.equals(unoNueve[j])) {
					miDNI += unoNueve[j];
				}
			}
		}
		if(miDNI.length() != 8) {
			return false;
		}else {
			return true;
        }
   }
}