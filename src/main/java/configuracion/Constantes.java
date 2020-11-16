/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracion;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import javax.servlet.http.Part;

/**
 *
 * @author erikssonherlo
 */
public class Constantes {
    //VARIABLES UTILIZADAS PARA OBTENER EL HORARIO DE TRABAJO DEL USUARIO
    private static LocalTime entradaUsuario;
    private static LocalTime salidaUsuario;
    private static LocalTime horarioActual;
    
    //HORA DEFINIDA POR DEFECTO PARA CALIFICACION
    private static final LocalTime HORARIO_MATUTINO_CALIFICACION = LocalTime.parse("11:00");
    private static final LocalTime HORARIO_VESPERTINO_CALIFICACION = LocalTime.parse("15:00");
    
    //PATH ABSOLUTO DE LA CARPETA DONDE SE GUARDAN LOS PDF, POR DEFECTO CAMBIAR
    private static final String PATH_ABSOLUTO_ARCHIVOS = "/home/erikssonherlo/NetBeansProjects/BILLETON/src/main/webapp/DPIClientes/";
    
    //HORARIOS PARA TURNO MATUTINO POR DEFECTO
    private static final String MATUTINO_HORARIO_ENTRADA = "07:00";
    private static final String MATUTINO_HORARIO_SALIDA = "14:00";
    
    //HORARIOS PARA TURNO VESPERTINO POR DEFECTO
    private static final String VESPERTINO_HORARIO_ENTRADA = "14:00";
    private static final String VESPERTINO_HORARIO_SALIDA = "19:00";
   
    //LIMITES PARA LOS REPORTES DEL GERENTE POR DEFECTO
    private static final double LIMITE_REPORTE_2_DEFECTO = 1000;
    private static final double LIMITE_REPORTE_3_DEFECTO = 10000;
    
    private static java.sql.Date fechaActual ; 
    //fechaActual = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    
    public static String getPATH_ABSOLUTO_ARCHIVOS() {
        return PATH_ABSOLUTO_ARCHIVOS;
    }

    public static Date getFechaActual() {
        return fechaActual;
    }

    public static String getMATUTINO_HORARIO_ENTRADA() {
        return MATUTINO_HORARIO_ENTRADA;
    }

    public static String getMATUTINO_HORARIO_SALIDA() {
        return MATUTINO_HORARIO_SALIDA;
    }

    public static String getVESPERTINO_HORARIO_ENTRADA() {
        return VESPERTINO_HORARIO_ENTRADA;
    }

    public static String getVESPERTINO_HORARIO_SALIDA() {
        return VESPERTINO_HORARIO_SALIDA;
    }

    public static LocalTime getEntradaUsuario() {
        return entradaUsuario;
    }

    public static LocalTime getSalidaUsuario() {
        return salidaUsuario;
    }

    public static LocalTime getHorarioActual() {
        return horarioActual;
    }

    public static LocalTime getHORARIO_MATUTINO_CALIFICACION() {
        return HORARIO_MATUTINO_CALIFICACION;
    }

    public static LocalTime getHORARIO_VESPERTINO_CALIFICACION() {
        return HORARIO_VESPERTINO_CALIFICACION;
    }

    public static double getLIMITE_REPORTE_2_DEFECTO() {
        return LIMITE_REPORTE_2_DEFECTO;
    }

    public static double getLIMITE_REPORTE_3_DEFECTO() {
        return LIMITE_REPORTE_3_DEFECTO;
    }
    

    
    public static void setEntradaUsuario(LocalTime entradaUsuario) {
        Constantes.entradaUsuario = entradaUsuario;
    }

    public static void setSalidaUsuario(LocalTime salidaUsuario) {
        Constantes.salidaUsuario = salidaUsuario;
    }

    public static void setHorarioActual(LocalTime horarioActual) {
        Constantes.horarioActual = horarioActual;
    }

    public static void setFechaActual(Date fechaActual) {
        Constantes.fechaActual = fechaActual;
    }
    
    
    
    public static String getStringUTF(String parametro) throws UnsupportedEncodingException {
    return new String(parametro.getBytes("ISO-8859-1"), "UTF-8");
}
    public static String getHoraActual(){
    SimpleDateFormat formateador = new SimpleDateFormat("HH:MM");
     return formateador.format(Constantes.getFechaActual());
    }
    public static void guardarArchivos(Part filePart,String rutaArchivo){
        
        File rutaDestino = new File(Constantes.getPATH_ABSOLUTO_ARCHIVOS());
        File file = new File(rutaDestino,rutaArchivo);

        try(InputStream inputS = filePart.getInputStream()) {
            
            Files.copy(inputS, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
}
