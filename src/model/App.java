package model;

import estructuras.ListaSimple;
import estructuras.Nodo;

import java.util.function.Predicate;

public class App {

    private ListaSimple listaProcesos;
    private ListaSimple<Usuario> listaUsuarios;

    private Usuario usuarioLogeado;

    private Proceso procesoSeleccionado;
    private Actividad actividadSeleccionada;

    public App() {
        this.listaProcesos = new ListaSimple<>();
        this.listaUsuarios = new ListaSimple<>();

        listaUsuarios.agregarFinal(new Usuario("carlos","123", Rol.ADMINISTRADOR));
    }

    public Usuario iniciarSesion(Usuario usuario) throws Exception {
        Nodo<Usuario> nodoUsuario = listaUsuarios.buscarNodo(usuario);

        if (nodoUsuario != null) {
            Usuario usuarioEnLista = nodoUsuario.getValorNodo();

            if (usuarioEnLista != null &&
                    usuarioEnLista.getUserId().equals(usuario.getUserId()) &&
                    usuarioEnLista.getPassword().equals(usuario.getPassword())) {
                System.out.println("Sesion iniciada con exito");
                return usuarioEnLista;

            }
        }

        throw new Exception("Error al autenticarse");
    }


    public Proceso crearProceso(String id, String nombre, ListaSimple<Actividad> listaActividades, int tiempoMinimo, int tiempoMaximo) {
        Nodo<Proceso> nodoProceso = listaProcesos.buscarNodo(new Proceso(id, nombre, listaActividades, tiempoMinimo, tiempoMaximo));

        if (nodoProceso != null) {
            throw new RuntimeException("Ya existe un proceso con ese ID");
        }

        Proceso nuevoProceso = new Proceso(id, nombre, listaActividades, tiempoMinimo, tiempoMaximo);
        listaProcesos.agregarInicio(nuevoProceso);
        System.out.println("Proceso creado con éxito");
        return nuevoProceso;
    }


    private Proceso configurarProceso(){
        return null;
    }

    public void insertarTareaAlFinal(Proceso proceso, Actividad actividad, Tarea tarea) {
        Nodo<Proceso> nodoProceso = listaProcesos.buscarNodo(proceso);

        if (nodoProceso != null) {
            Proceso procesoEncontrado = (Proceso) nodoProceso.getValorNodo();

            if (procesoEncontrado != null) {
                ListaSimple<Actividad> listaActividades = procesoEncontrado.getListaActividades();

                if (listaActividades != null) {
                    Actividad actividadEncontrada = listaActividades.buscarNodo(actividad).getValorNodo();

                    if (actividadEncontrada != null) {
                        actividadEncontrada.getTareas().encolar(tarea);
                    }
                }
            }
        }
    }

    public double consultarTiempoDuracionProceso(Proceso proceso) {
        if (proceso != null) {
            return (proceso.getTiempoMaximo() + proceso.getTiempoMinimo()) / 2.0;
        }

        return 0;
    }

    public Usuario crearCuentasDeUsuario(String userId, String password, Rol rol) {
        Usuario newUser = new Usuario(userId, password, rol);
        Usuario user;
        for (int i = 0; i < listaUsuarios.getTamano(); i++) {
            user = (Usuario) listaUsuarios.getNode(i).getValorNodo();
            if(user.getUserId().equals(userId)){
                return null;
            }
        }
        return newUser;
    }


    private String notificarRecordatorios(){
        return null;
    }
    private String importarYExportarInformacion(){
        return null;
    }

    public ListaSimple getListaProcesos() {
        return listaProcesos;
    }

    public void setListaProcesos(ListaSimple listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public ListaSimple getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ListaSimple listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }


    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public Proceso getProcesoSeleccionado() {
        return procesoSeleccionado;
    }

    public void setProcesoSeleccionado(Proceso procesoSeleccionado) {
        this.procesoSeleccionado = procesoSeleccionado;
    }

    public Actividad getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Actividad actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }
}
