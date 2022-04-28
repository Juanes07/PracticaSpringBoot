package com.crud.democrud.utility;

public class Respuesta {

    public Boolean error;
    public String mensaje;
    public Object data;

    /**
     * Class constructor
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    public Respuesta() {
        error = false;
        mensaje = "";
        data = null;
    }

    /**
     * Default values of the response
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    public void Reiniciar() {
        error = false;
        mensaje = "";
        data = null;
    }
}
