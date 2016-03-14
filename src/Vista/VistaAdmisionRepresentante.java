/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Calendario.Calendario;
import Controlador.Aceptar;
import Controlador.Cancelar;
import Controlador.CerrarVentana;
import Controlador.OyenteAceptar;
import Controlador.OyenteCancelar;
import Controlador.ValidadorCorreo;
import Modelo.Representante;
import Vista.Formatos.Botonera;
import Vista.Formatos.CampoAreaTexto;
import Vista.Formatos.CampoCombo;
import Vista.Formatos.CampoTexto;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author josediaz
 */
public final class VistaAdmisionRepresentante extends JFrame implements Aceptar, Cancelar, CerrarVentana{
    private final CampoTexto cedula,nombres,apellidos,telefono,correo,fechanac;
    private final CampoAreaTexto direccion;
    private final CampoCombo sexo;
    private final JPanel panelTop;
    private final Botonera boton;
    private final String[] AC = {"Aceptar","Cancelar"};
    private final String[] opcSexo = {"","Masculino","Femenino"};

    public VistaAdmisionRepresentante(){
        setTitle("Ingreso de Representante");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /**
         * Elementos del panel superior
         */
        cedula = new CampoTexto("Cedula",20);
        cedula.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int escrito = ke.getKeyChar();
                if((escrito<'0' || escrito>'9')) ke.consume(); 
                if(cedula.longuitudDelContenido() >= 8) ke.consume(); 
            }
        });
        
        nombres = new CampoTexto("Nombres",20);
        nombres.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char escrito = ke.getKeyChar();
                if((escrito!='a') && (escrito!='b') && (escrito!='c') && (escrito!='d') &&
                   (escrito!='e') && (escrito!='f') && (escrito!='g') && (escrito!='h') &&
                   (escrito!='i') && (escrito!='j') && (escrito!='k') && (escrito!='l') &&
                   (escrito!='m') && (escrito!='n') && (escrito!='ñ') && (escrito!='o') &&
                   (escrito!='p') && (escrito!='q') && (escrito!='r') && (escrito!='s') &&
                   (escrito!='t') && (escrito!='u') && (escrito!='v') && (escrito!='w') &&
                   (escrito!='x') && (escrito!='y') && (escrito!='z') && (escrito!='A') &&
                   (escrito!='B') && (escrito!='C') && (escrito!='D') && (escrito!='E') &&
                   (escrito!='F') && (escrito!='G') && (escrito!='H') && (escrito!='I') &&
                   (escrito!='J') && (escrito!='K') && (escrito!='L') && (escrito!='M') &&
                   (escrito!='N') && (escrito!='Ñ') && (escrito!='O') && (escrito!='P') &&
                   (escrito!='Q') && (escrito!='R') && (escrito!='S') && (escrito!='T') &&
                   (escrito!='U') && (escrito!='V') && (escrito!='W') && (escrito!='X') &&
                   (escrito!='Y') && (escrito!='Z') && (escrito!='Á') && (escrito!='á') &&
                   (escrito!='É') && (escrito!='é') && (escrito!='Í') && (escrito!='í') &&
                   (escrito!='Ó') && (escrito!='ó') && (escrito!='Ú') && (escrito!='ú') &&
                   (escrito!='Ä') && (escrito!='ä') && (escrito!='Ë') && (escrito!='ë') &&
                   (escrito!='Ï') && (escrito!='ï') && (escrito!='Ö') && (escrito!='ö') &&
                   (escrito!='Ü') && (escrito!='ü') && (escrito!=KeyEvent.VK_BACK_SPACE) &&
                   (escrito!=KeyEvent.VK_SPACE)) ke.consume();
                if(nombres.longuitudDelContenido() >= 45) ke.consume();
            }
        });
        
        apellidos = new CampoTexto("Apellidos",20);
        apellidos.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char escrito = ke.getKeyChar();
                if((escrito!='a') && (escrito!='b') && (escrito!='c') && (escrito!='d') &&
                   (escrito!='e') && (escrito!='f') && (escrito!='g') && (escrito!='h') &&
                   (escrito!='i') && (escrito!='j') && (escrito!='k') && (escrito!='l') &&
                   (escrito!='m') && (escrito!='n') && (escrito!='ñ') && (escrito!='o') &&
                   (escrito!='p') && (escrito!='q') && (escrito!='r') && (escrito!='s') &&
                   (escrito!='t') && (escrito!='u') && (escrito!='v') && (escrito!='w') &&
                   (escrito!='x') && (escrito!='y') && (escrito!='z') && (escrito!='A') &&
                   (escrito!='B') && (escrito!='C') && (escrito!='D') && (escrito!='E') &&
                   (escrito!='F') && (escrito!='G') && (escrito!='H') && (escrito!='I') &&
                   (escrito!='J') && (escrito!='K') && (escrito!='L') && (escrito!='M') &&
                   (escrito!='N') && (escrito!='Ñ') && (escrito!='O') && (escrito!='P') &&
                   (escrito!='Q') && (escrito!='R') && (escrito!='S') && (escrito!='T') &&
                   (escrito!='U') && (escrito!='V') && (escrito!='W') && (escrito!='X') &&
                   (escrito!='Y') && (escrito!='Z') && (escrito!='Á') && (escrito!='á') &&
                   (escrito!='É') && (escrito!='é') && (escrito!='Í') && (escrito!='í') &&
                   (escrito!='Ó') && (escrito!='ó') && (escrito!='Ú') && (escrito!='ú') &&
                   (escrito!='Ä') && (escrito!='ä') && (escrito!='Ë') && (escrito!='ë') &&
                   (escrito!='Ï') && (escrito!='ï') && (escrito!='Ö') && (escrito!='ö') &&
                   (escrito!='Ü') && (escrito!='ü') && (escrito!=KeyEvent.VK_BACK_SPACE) &&
                   (escrito!=KeyEvent.VK_SPACE)) ke.consume();
                if(apellidos.longuitudDelContenido() >= 45) ke.consume();
            }
        });
        
        telefono = new CampoTexto("Telefono",20);
        telefono.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int escrito = ke.getKeyChar();
                if(escrito<'0' || escrito>'9') ke.consume(); 
                if(telefono.longuitudDelContenido() >= 10){ 
                    ke.consume();
                    JOptionPane.showMessageDialog(null, "Maximo 10 números para entradas de telefono, ejemplo: 4144456677");
                }
            }
        });
        
        direccion = new CampoAreaTexto("Dirección",20,2);
        direccion.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char escrito = ke.getKeyChar();
                if((escrito!='a') && (escrito!='b') && (escrito!='c') && (escrito!='d') &&
                   (escrito!='e') && (escrito!='f') && (escrito!='g') && (escrito!='h') &&
                   (escrito!='i') && (escrito!='j') && (escrito!='k') && (escrito!='l') &&
                   (escrito!='m') && (escrito!='n') && (escrito!='ñ') && (escrito!='o') &&
                   (escrito!='p') && (escrito!='q') && (escrito!='r') && (escrito!='s') &&
                   (escrito!='t') && (escrito!='u') && (escrito!='v') && (escrito!='w') &&
                   (escrito!='x') && (escrito!='y') && (escrito!='z') && (escrito!='A') &&
                   (escrito!='B') && (escrito!='C') && (escrito!='D') && (escrito!='E') &&
                   (escrito!='F') && (escrito!='G') && (escrito!='H') && (escrito!='I') &&
                   (escrito!='J') && (escrito!='K') && (escrito!='L') && (escrito!='M') &&
                   (escrito!='N') && (escrito!='Ñ') && (escrito!='O') && (escrito!='P') &&
                   (escrito!='Q') && (escrito!='R') && (escrito!='S') && (escrito!='T') &&
                   (escrito!='U') && (escrito!='V') && (escrito!='W') && (escrito!='X') &&
                   (escrito!='Y') && (escrito!='Z') && (escrito!='Á') && (escrito!='á') &&
                   (escrito!='É') && (escrito!='é') && (escrito!='Í') && (escrito!='í') &&
                   (escrito!='Ó') && (escrito!='ó') && (escrito!='Ú') && (escrito!='ú') &&
                   (escrito!='Ä') && (escrito!='ä') && (escrito!='Ë') && (escrito!='ë') &&
                   (escrito!='Ï') && (escrito!='ï') && (escrito!='Ö') && (escrito!='ö') &&
                   (escrito!='Ü') && (escrito!='ü') && (escrito<'0' || escrito>'9') &&
                   (escrito!=KeyEvent.VK_SPACE) && (escrito!=KeyEvent.VK_BACK_SPACE) && 
                   (escrito!=KeyEvent.VK_COMMA) && (escrito!=KeyEvent.VK_PERIOD)) ke.consume();
                if(direccion.longuitudDelContenido() >= 255) ke.consume();
            }
        });
        
        correo = new CampoTexto("Correo",20);
        correo.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char escrito = ke.getKeyChar();
                //System.out.println(escrito);
                if((escrito!='a') && (escrito!='b') && (escrito!='c') && (escrito!='d') &&
                   (escrito!='e') && (escrito!='f') && (escrito!='g') && (escrito!='h') &&
                   (escrito!='i') && (escrito!='j') && (escrito!='k') && (escrito!='l') &&
                   (escrito!='m') && (escrito!='n') && (escrito!='o') &&
                   (escrito!='p') && (escrito!='q') && (escrito!='r') && (escrito!='s') &&
                   (escrito!='t') && (escrito!='u') && (escrito!='v') && (escrito!='w') &&
                   (escrito!='x') && (escrito!='y') && (escrito!='z') && (escrito!='A') &&
                   (escrito!='B') && (escrito!='C') && (escrito!='D') && (escrito!='E') &&
                   (escrito!='F') && (escrito!='G') && (escrito!='H') && (escrito!='I') &&
                   (escrito!='J') && (escrito!='K') && (escrito!='L') && (escrito!='M') &&
                   (escrito!='N') && (escrito!='O') && (escrito!='P') &&
                   (escrito!='Q') && (escrito!='R') && (escrito!='S') && (escrito!='T') &&
                   (escrito!='U') && (escrito!='V') && (escrito!='W') && (escrito!='X') &&
                   (escrito!='Y') && (escrito!='Z') && (escrito!='@') && (escrito!='_') &&
                   (escrito!=KeyEvent.VK_BACK_SPACE) && (escrito!=KeyEvent.VK_PERIOD) &&
                   (escrito<'0' || escrito>'9') &&
                   (escrito!=KeyEvent.VK_MINUS) && (escrito!=KeyEvent.VK_SPACE)
                ) ke.consume();
                if(correo.longuitudDelContenido() >= 45) ke.consume();
            }
        });
        
        fechanac = new CampoTexto("Fecha de Nacimiento",20);
        fechanac.campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int escrito = ke.getKeyChar();
                if((escrito<'0' || escrito>'9') && (escrito!=KeyEvent.VK_MINUS)) ke.consume(); 
                if(fechanac.longuitudDelContenido() >= 10) ke.consume(); 
            }
        });
        
        sexo = new CampoCombo("Sexo",opcSexo);
        
        /**
         * Invoca calendario al enfocar
         */
        fechanac.campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fechanac.obtenerContenido().length() == 0) {
                    Calendario calendario = new Calendario(fechanac.campo);
                }
            }
        });
        
        panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(3,3));
        panelTop.add(cedula);
        panelTop.add(nombres);
        panelTop.add(apellidos);
        panelTop.add(telefono);
        panelTop.add(direccion);
        panelTop.add(correo);
        panelTop.add(fechanac);
        panelTop.add(sexo);
        
        /**
         * Elementos inferiores
         */
        boton=new Botonera(AC);
        boton.adherirEscucha(0, new OyenteAceptar(this));
        boton.adherirEscucha(1, new OyenteCancelar(this));

        /**
         * Configuracion de Vista
         */
        add(BorderLayout.NORTH,panelTop);
        add(BorderLayout.SOUTH,boton);
        pack();
        setVisible(true);
    }

    @Override
    public void aceptar() {
        Representante representante = new Representante();
        
        if (cedula.longuitudDelContenido() != 0 && nombres.longuitudDelContenido() != 0 &&
        apellidos.longuitudDelContenido() != 0 && telefono.longuitudDelContenido() != 0 &&
        direccion.longuitudDelContenido() != 0 && correo.longuitudDelContenido() != 0 &&
        fechanac.longuitudDelContenido() != 0 && sexo.obtenerSeleccion().toString().length() != 0) {
            
            ValidadorCorreo correoValido = new ValidadorCorreo();
            
            if (correoValido.validarCorreo(correo.obtenerContenido())) {
            
                String strCedulaRep = cedula.obtenerContenido();
                int cedulaRep = Integer.parseInt(strCedulaRep);

                String nombreRep = nombres.obtenerContenido();
                String apellidoRep = apellidos.obtenerContenido();
                String telefonoRep = telefono.obtenerContenido();
                String direccionRep = direccion.obtenerContenido();
                String correoRep = correo.obtenerContenido();
                String fechaNacRep = fechanac.obtenerContenido();
                String sexoRep = sexo.obtenerSeleccion().toString();

                if (representante.incluir(cedulaRep, nombreRep, apellidoRep, telefonoRep, direccionRep, correoRep, fechaNacRep, sexoRep)) {
                    cerrarVentana();
                } else {
                    JOptionPane.showMessageDialog(this,"Error al insertar");
                }
            } else {
                JOptionPane.showMessageDialog(this,"El correo es invalido");
            }
        } else {
            JOptionPane.showMessageDialog(this,"Existen campos vacios");
        }
    }

    @Override
    public void cancelar() {
        cerrarVentana();
    }

    @Override
    public void cerrarVentana() {
        this.dispose();
    }
}
