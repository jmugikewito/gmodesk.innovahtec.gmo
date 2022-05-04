package gmo.internal;

import app.RunMain;
import color.MaterialColor;
import static gmo.core.MainLite.*;
import gmo.dialog.DuplicarTareo;
import gmo.dialog.GenerarAsistencia;
import gmo.dialog.SendNisira;
import gmo.dialog.panDuplicarTareo;
import java.awt.CardLayout;
import java.awt.Window;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import jmugi.voids.JMethods;

import gmo.methods.jmethods;
import gmo.utils.jkeys;
import gmo.utils.jvalues;
import iconfont.MATERIALICON;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import jmugi.model.Parameter;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JCallback;
import jmugi.voids.JCallbackFrame;
import jmugi.voids.PrintMethods;
import jmugi.voids.gmoEncript;
import jmugi.voids.gmoEncript2022;
import kevin.component.defaults;
import kevin.component.dialog.MaterialSmartDialog;
import kevin.component.dialog.SmartLoader;
import kevin.component.dialog.TableDialog;
import kevin.component.frame.GMOInternalFrame;
import kevin.component.toast.Toast;
import other.tables.FormatAdminTareosNisira;
import utils.ExecHTTP;
import views.JMDialog.PDialogInputArea;
import views.JMWindow.Message;

public class AdminTareosOnLine extends GMOInternalFrame {

    JInternalFrame internal;
    String IDTAREO, IDUSUARIO, IDESTADO_OLD, OBSERVACIONES = "", OBSERVACIONES2 = "";
    String FECHAPROGRAMACION, IDPLANILLA, IDTURNO, IDESTADO = "NI";
    public CardLayout CARDLAYOUT;
    String LISTAMAILS = "";
    String MAILDESTINO = "";
    String DOCUMENTO = "";
    String DETALLE = "", HORASE = "", HORASB = "", TRAZABILIDAD = "";
    int VALUE = 0;

    String panelShow = "cardData";

    String TIPOTAREO;
    AdminSubTareos editTareo;
    boolean isTareoHTEC = true;
    String FECHA_DATE1;
    boolean PUEDE_EDITAR = false;
    boolean viewFilter = true;

    public String TOKEN = "";
    
    JFrame frame;
    JCallbackFrame SAVE_CALLBACK;
    
    public AdminTareosOnLine(Window w) {
        this.Frame = w;
        this.frame = (JFrame) (java.awt.Frame) w;
        initComponents();

        chooserDate1.setDate(new Date());
        FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserDate1.getDate());
        chooserDate1.setCallback(() -> {
            FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserDate1.getDate());
        });

        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        internal = this;
        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, panelShow);
        executrePrivilegiostoExportNisira("NI");

        validateConsolidado();

        lblModloLigero.setVisible(true);
        swi_modoLigero.setVisible(true);

        switch (jkeys.RUC) {
            case "20103272964":
                mi_detallecons.setVisible(false);
                break;
        }

        jmethods.cargarPlanillas(cboPlanillas, " 'u','8' ", jvalues.USUARIO.getFirma().length() > 0 ? gmoEncript2022.encriptar(JMethods.splitStringComa(jvalues.USUARIO.getFirma())) : "''");
        iniAccess();
    }

    public void iniAccess() {
        edt_usuario.setText(jkeys.IDUSUARIO);
        PUEDE_EDITAR = jvalues.USUARIO.getTipousuario().getEsdigitador1() == 1 || jvalues.USUARIO.getTipousuario().getEsdigitador1() == 1;
        if (PUEDE_EDITAR == false)
            PUEDE_EDITAR = jvalues.USUARIO.getTipousuario().getEdita() == 1;

        toolbar1.setEDITAR(false);
        toolbar1.setRECARGAR(true);
        toolbar1.setFILTRAR(true);
        toolbar1.setEXCEL(true);
        toolbar1.setREGRESAR(false);
        toolbar1.setGUARDAR(false); //NUEVO
        toolbar1.setRECARGAR_CALLBACK(this::gettin_data);
        toolbar1.setEXCEL_CALLBACK(() -> {
            switch (panelShow) {
                case "cardData":
                    tabla.exportExcel((JFrame) Frame, "Exportando Tareos...");
                    break;
                case "cardDesconocidos":
                    tablaUnknow.exportExcel((JFrame) Frame, "Exportando Trabajadores Desconocidos...");
                    break;
            }
        });
        toolbar1.setFILTRAR_CALLBACK(() -> {
            panelFilter.setVisible(!viewFilter);
            viewFilter = !viewFilter;
        });
        toolbar1.setEDITAR_CALLBACK(() -> {
            goEditar();
        });
        toolbar1.setREGRESAR_CALLBACK(() -> {
            panelShow = "cardData";
            CARDLAYOUT.show(contenedor, panelShow);
        });
    }

    private void validateConsolidado() {
        switch (jvalues.USUARIO.getTipousuario().getIdtipousuario()) {
            case "JEFEPRODPACK": //SI 9
            case "JEFECOSECHA": //NO TIENE EL REPORTE
            case "JEFERRHH": //SI 9
            case "JEFEPLANIF": //SI 9
            case "HTECUSER": //SI 9
            case "ADMINIST": //TIPOUSUARIO NO EXISTE
                cbo_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"PE", "PA", "AN", "AP", "NI", "RE", "OB", "CO", "DU"}));
                break;
            case "ANALYSTRRHH": //SI 7
            case "ANALYSTRRHHPACK"://SI 7
            case "AUXRRHH"://NO TIENE EL REPORTE
            case "COORDRRHH": //TIPOUSUARIO SIN ACCESO
            case "COORDRRHHPACK"://TIPOUSUARIO SIN ACCESO
            //    cbo_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"PE", "AP", "PA", "AN", "NI", "RE", "OB"}));
            //    break;
            case "SUPERRRHH"://SI 7
            case "SUPERRRHHPACK"://SI 7
                cbo_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"PE", "PA", "AN", "AP", "NI", "RE", "OB"}));
                break;
            default://SI 3 AL RESTO DE USUARIO
                cbo_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"PE", "PA", "AN"}));
                break;
        }
    }

    public void setOnOf(boolean es) {
        swi_ennisira.setOnOff(es);
        VALUE = 1;
    }

    public void setConsolidado(boolean select) {
        if (select) {
            edt_usuario.setText("");
        }
    }

    public void gettin_data() {
        if (swi_ennisira.isOnOff()) {
            VALUE = 1;
            setTitle("Seguimiento de Tareos en ERP Externo");

        } else {
            VALUE = 0;
            setTitle("Seguimiento de Tareos en el Servidor");
        }
                
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos del Seguimiento de Tareos",
                "Se estan Descargando los datos...",
                (Window frame) -> {                    
                    tabla.loadApiDataSmart(
                                        swi_modoLigero.isOnOff() ? "api/desk/mano-de-obra/listar-tareos-swift" : "api/desk/mano-de-obra/listar-tareos-swift2",
                                        "denisira,iddatabase,idempresa,idusuario,idestado,fecha,deestemes,idplanilla",
                                        VALUE, jkeys.IDDATABASE, jkeys.IDEMPRESA, edt_usuario.getText(), cbo_estado.getSelectedItem().toString().trim(), 
                                        FECHA_DATE1.replace("-", ""), (swi_porEsteMes.isOnOff() ? 1 : 0), cboPlanillas.getIditem()
                                );                   
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
        load = null;
        JMethods.updateInternalJTable(this, tabla);
        
        if (!swi_ennisira.isOnOff()) {
            Toast.makeText((JFrame) Frame, "Lista de Tareos Actualizados", Toast.Style.SUCCESS).display();
        }

        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos",
                "Actualizando Lista de Tareos con estado \"" + cbo_estado.getSelectedItem().toString().trim() + "\"", (Window frame) -> {
            //tabla.GetDatosHTTP2022();
            if (tabla.getRowCount() > 0) {
                if (swi_ennisira.isOnOff()) {
                    tabla.setComponentPopupMenu(popupNisira);
                } else {
                    tabla.setComponentPopupMenu(popup);
                }
            } else {
                tabla.setComponentPopupMenu(null);
            }
            CARDLAYOUT.show(contenedor, "cardData");
            load.dispose();

            JDialog.setDefaultLookAndFeelDecorated(true);
        });
        JMethods.settingGlassPane((JFrame) Frame, load, MaterialColor.BLUEGREY_900, 0.8f);
        load = null;
        panelFilter.setVisible(!viewFilter);
        viewFilter = !viewFilter;
        JMethods.updateInternalJTable(internal, tabla);
        mi_supervisor.setVisible(false);

    }

    private void updateSupervisor() {
        if (tabla.getSelectedColumn() >= 1) {
            int ROW_COUNT = tabla.getSelectedRowCount();
            if (ROW_COUNT == 1) {
                OBSERVACIONES2 = "La modificacion del supervisor en el tareo:" + IDTAREO + " en el estado:" + IDESTADO + " en GMO DESK fue realizada por el usuario " + jkeys.IDUSUARIO;
            } else if (ROW_COUNT > 1) {
                String idtareo = JMethods.GetColumnSelect(tabla, 0);
                OBSERVACIONES2 = "La modificacion del supervisor en los tareos:" + idtareo + " en el estado:" + IDESTADO + " en GMO DESK fue realizada por el usuario " + jkeys.IDUSUARIO;
            }
            /*int[] sel = tabla.getSelectedRows();
            String x = JOptionPane.showInputDialog(this.getContentPane(), "Ingrese DNI de Supervisor:");
            //ArrayList<Object[]> SQL = Main.CONECT.GetData(ExecHTTP.parseQuery( "select nombresall from trabajador where idtrabajador=?1;", true, x));
            //String v1 = Arrays.toString(SQL.get(0));
            //String v2 = v1.substring(1,v1.length()-1);
            do{
                            
                for (int i = 0; i < sel.length; i++) {
                                tabla.setValueAt(x, sel[i], 4);
                                tabla.setValueAt("Supervisor Cambiado" , sel[i], 5);//NUEVO
                                //String estado = tabla.getValueAt(0,9).toString();
                                //PrintMethods.printer("El estado del tareo es: "+estado);
                                if (tabla.getValueAt(sel[i], tabla.getColumnCount() - 1).toString().equals("0"))
                                    tabla.setValueAt("1", sel[i], tabla.getColumnCount() - 1);
                            }
                            
             }while (!x.equals("")); */

            PDialogInputArea.PDialogShow(Frame, "Ingrese DNI de Supervisor: ",
                    () -> {

                        applyCambioSupervisor(PDialogInputArea.input);
                    },
                    () -> {
                        PrintMethods.printer("");
                    });
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un Tareo");
        }
    }

    private void executrePrivilegiostoExportNisira(String idestado) {
        if (!jvalues.USUARIO.getIdusuario().equals("JUSUARIO")) {
            switch (jvalues.USUARIO.getTipousuario().getIdtipousuario()) {
                case "SUPERRRHH":
                case "SUPERRRHHPACK":
                case "JEFERRHH":
                case "HTECUSER":
                    switch (idestado) {
                        case "PA":
                            settinMenu(false, true, true, true, false, false);
                            break;
                        case "PE":
                            settinMenu(true, false, true, true, false, false);
                            break;
                        case "AP":
                            settinMenu(false, false, true, true, false, false);
                            break;
                        case "NI":
                            settinMenu(true, false, false, false, false, false);
                            break;
                        case "DU":
                            mn_admin.setVisible(false);
                            mi_toNisira.setVisible(false);
                            mi_toNisiraDu.setVisible(false);
                            break;
                        default:
                            settinMenu(false, false, false, false, false, false);
                            break;
                    }
                    mn_admin.setVisible(true);
                    break;
                case "COORDRRHHPACK":
                case "ANALYSTRRHHPACK":
                case "ANALYSTRRHH":
                case "ADMINIST":
                    switch (idestado) {
                        case "PA":
                            settinMenu(false, true, true, true, false, false);
                            break;
                        case "PE":
                            settinMenu(true, false, true, true, false, false);
                            break;
                        case "AP":
                            settinMenu(false, false, true, true, false, false);
                            break;
                        case "NI":
                            settinMenu(false, false, false, false, false, false);
                            break;
                        case "DU":
                            mn_admin.setVisible(false);
                            mi_toNisira.setVisible(false);
                            mi_toNisiraDu.setVisible(false);
                            break;
                        default:
                            settinMenu(false, false, false, false, false, false);
                            break;
                    }
                    mn_admin.setVisible(true);
                    break;
                default:
                    settinMenu(false, false, false, false, false, false);
                    break;
            }

        } else {
            mi_toNisira.setVisible(true);
            mi_toNisiraDu.setVisible(true);
        }
    }

    public void aprobarPendienteAtrasado() {
        Parameter p;
        for (int i = 0; i < jvalues.LIST_PARAMETROS.size(); i++) {
            if (jvalues.LIST_PARAMETROS.get(i).getIdparametro().equals("APROBACION_PARCIAL_" + cboPlanillas.getIditem())) {
                p = jvalues.LIST_PARAMETROS.get(i);
                String hora_hoy = DateTimeUtil.getTime_HHmmss(new Date());
                String hora_permitida = p.getValor2();
                double x = DateTimeUtil.timeToDecimal(DateTimeUtil.restar_time(DateTimeUtil.DateToString(new Date()) + " " + hora_hoy, DateTimeUtil.DateToString(new Date()) + " " + hora_permitida));
                if (x > 0) {
                    cambioEstado("PE");
                } else {
                    Toast.mostarInfo(Frame, "A esta hora no se le permite aprobar Pendientes Aprobados", false);
                }
            }
        }
    }

    /**
     APROBAR - APROBARPE - RECHAZARA - OBSERVAR - NISIRA - NISIRADU

     @param e
     */
    private void settinMenu(boolean... e) {
        mi_aprobar.setVisible(e[0]);
        mi_aprobarpe.setVisible(e[1]);
        mi_rechazar.setVisible(e[2]);
        mi_observar.setVisible(e[3]);

        mi_toNisira.setVisible(e[4]); //EXPORTAR TAREO EXTERNO
        mi_toNisiraDu.setVisible(e[5]); //DUPLICAR TAREO A NISIRA
    }

    private void goDetalle() {

        if (tabla.getSelectedRowCount() > 0) {
            IDTAREO = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            DETALLE = tabla.getValueAt(tabla.getSelectedRow(), 13).toString();

            JDialog.setDefaultLookAndFeelDecorated(false);
            load = new SmartLoader((java.awt.Frame) Frame, true,
                    "Descargando Datos del Personal SubTareado",
                    "Se estan Descargando los datos de los subtareos",
                    (Window frame) -> {                    
                        tabla.loadApiDataSmart(
                                            "api/desk/mano-de-obra/detalle-tareo",
                                            "iddatabase,idtareo,esnisira,resumido",
                                            jkeys.IDEMPRESA, IDTAREO, 0, 0
                                    );                   
                        load.dispose();
                        JDialog.setDefaultLookAndFeelDecorated(true);
                    });

            JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
            load = null;
            JMethods.updateInternalJTable(this, tabla);
        } else {
            Toast.mostarInfo((JFrame) Frame, "Seleccione un Tareo", true);
        }
    }

    private void getDesconocidos() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos...",
                "Se estan Descargando los Trabajadores Desconocidos...",
                (Window frame) -> {
                    tablaUnknow.initHttp("0",
                            "idtareo,doc,idusuario,fecha,dni,nombres,jor,rend,ava",
                            "idtareo,doc,idusuario,fecha,dni,nombres,jor,rend,ava",
                            "Stringx3,DateSQLx1,Stringx2,Doublex3",
                            gettin_pages.api_get() + ExecHTTP.parseQL("exec GetLisTareoTrabajadoresUnknow ", (""), jkeys.IDEMPRESA, chooserDate1.toStringDate(), chooserDate1.toStringDate(), 0)
                    );
                    tablaUnknow.GetDatosHTTP2022();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, color.MaterialColor.BLUEGREY_900, 0.8f);
        load = null;
        JMethods.updateInternalJTable(this, tablaUnknow);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mi_editarTareoOnline = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        popup = new javax.swing.JPopupMenu();
        mi_observaciones = new javax.swing.JMenuItem();
        mn_resumen = new javax.swing.JMenu();
        mi_detalle = new javax.swing.JMenuItem();
        mi_detallecons = new javax.swing.JMenuItem();
        mn_admin = new javax.swing.JMenu();
        mi_aprobar = new javax.swing.JMenuItem();
        mi_aprobarpe = new kevin.component.menu.GMOMenuItem();
        mi_rechazar = new javax.swing.JMenuItem();
        mi_observar = new javax.swing.JMenuItem();
        mi_supervisor = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mi_toNisira = new javax.swing.JMenuItem();
        mi_toNisiraDu = new javax.swing.JMenuItem();
        popupNisira = new javax.swing.JPopupMenu();
        mi_generarAsistencia = new javax.swing.JMenuItem();
        mi_eliminarTareo = new javax.swing.JMenuItem();
        btnDesconocidos = new kevin.component.button.ButtonMaterialIcon();
        mi_toNisiraAP = new javax.swing.JMenuItem();
        etiqueta2 = new kevin.component.label.Etiqueta();
        swi_ennisira = new kevin.component.switchbox.SwitchBox();
        lblModloLigero = new kevin.component.label.Etiqueta();
        swi_modoLigero = new kevin.component.switchbox.SwitchBox();
        jPanel3 = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        cardData = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        panelFilter = new javax.swing.JPanel();
        panel1 = new kevin.component.panel.Panel();
        jPanel6 = new javax.swing.JPanel();
        etiqueta3 = new kevin.component.label.Etiqueta();
        swi_porEsteMes = new kevin.component.switchbox.SwitchBox();
        edt_usuario = new kevin.component.edittext.EditText();
        jLabel3 = new javax.swing.JLabel();
        etiqueta5 = new kevin.component.label.Etiqueta();
        chooserDate1 = new kevin.component.date.MaterialDateChooser();
        etiqueta6 = new kevin.component.label.Etiqueta();
        cbo_estado = new kevin.component.combobox.ComboBox();
        jLabel2 = new javax.swing.JLabel();
        etiqueta7 = new kevin.component.label.Etiqueta();
        cboPlanillas = new kevin.component.combobox.ComboBox();
        btnConsultar = new kevin.component.button.Button();
        cardGraphics = new javax.swing.JPanel();
        cardDesconocidos = new kevin.component.panel.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUnknow = new kevin.component.tabla.TablaSmart();
        button1 = new kevin.component.button.Button();
        toolbar1 = new kevin.component.toolbar.Toolbar();

        mi_editarTareoOnline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit24b.png"))); // NOI18N
        mi_editarTareoOnline.setText("Editar Tareo del Servidor");
        mi_editarTareoOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_editarTareoOnlineActionPerformed(evt);
            }
        });

        mi_observaciones.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.QUESTION_ANSWER, defaults.colorAccent,24f));
        mi_observaciones.setText("Observaciones");
        mi_observaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_observacionesActionPerformed(evt);
            }
        });
        popup.add(mi_observaciones);

        mn_resumen.setText("Consolidados");

        mi_detalle.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.LIBRARY_BOOKS, defaults.colorAccent, 24f));
        mi_detalle.setText("Detalle");
        mi_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_detalleActionPerformed(evt);
            }
        });
        mn_resumen.add(mi_detalle);

        mi_detallecons.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.VIEW_COMPACT, defaults.colorAccent,24f));
        mi_detallecons.setText("Detalle Consumidores");
        mi_detallecons.setToolTipText("");
        mi_detallecons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_detalleconsActionPerformed(evt);
            }
        });
        mn_resumen.add(mi_detallecons);

        popup.add(mn_resumen);

        mn_admin.setText("Administracion");

        mi_aprobar.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.THUMB_UP, defaults.colorPrimary,24f));
        mi_aprobar.setText("Aprobar");
        mi_aprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_aprobarActionPerformed(evt);
            }
        });
        mn_admin.add(mi_aprobar);

        mi_aprobarpe.setText("Aprobar Tareo Retrazado");
        mi_aprobarpe.setICO(iconfont.MATERIALICON.MATERIALICONIC.EVENT_BUSY);
        mi_aprobarpe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_aprobarpeActionPerformed(evt);
            }
        });
        mn_admin.add(mi_aprobarpe);

        mi_rechazar.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.THUMB_DOWN, MaterialColor.RED_500,24f));
        mi_rechazar.setText("Rechazar");
        mi_rechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_rechazarActionPerformed(evt);
            }
        });
        mn_admin.add(mi_rechazar);

        mi_observar.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.SPEAKER_NOTES, defaults.colorAccent,24f));
        mi_observar.setText("Observar");
        mi_observar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_observarActionPerformed(evt);
            }
        });
        mn_admin.add(mi_observar);

        popup.add(mn_admin);

        mi_supervisor.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.QUESTION_ANSWER, defaults.colorAccent,24f));
        mi_supervisor.setText("Editar Supervisor");
        mi_supervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_supervisorActionPerformed(evt);
            }
        });
        popup.add(mi_supervisor);
        popup.add(jSeparator1);

        mi_toNisira.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.SEND, MaterialColor.INDIGOA_700,24f));
        mi_toNisira.setText("Exportar Tareo Externo");
        mi_toNisira.setEnabled(false);
        mi_toNisira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_toNisiraActionPerformed(evt);
            }
        });
        popup.add(mi_toNisira);

        mi_toNisiraDu.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.SEND, MaterialColor.INDIGO_300,24f));
        mi_toNisiraDu.setText("Duplicar Tareo a Nisira");
        mi_toNisiraDu.setEnabled(false);
        mi_toNisiraDu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_toNisiraDuActionPerformed(evt);
            }
        });
        popup.add(mi_toNisiraDu);

        mi_generarAsistencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/enviarAsistencia24.png"))); // NOI18N
        mi_generarAsistencia.setText("Generar Asistencia");
        mi_generarAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_generarAsistenciaActionPerformed(evt);
            }
        });
        popupNisira.add(mi_generarAsistencia);

        mi_eliminarTareo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/deleteGray24.png"))); // NOI18N
        mi_eliminarTareo.setText("Eliminar Tareo");
        mi_eliminarTareo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_eliminarTareoActionPerformed(evt);
            }
        });
        popupNisira.add(mi_eliminarTareo);

        btnDesconocidos.setICO(iconfont.MATERIALICON.MATERIALICONIC.PORTRAIT);
        btnDesconocidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconocidosActionPerformed(evt);
            }
        });

        mi_toNisiraAP.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.SEND, MaterialColor.INDIGOA_700,24f));
        mi_toNisiraAP.setText("Aprobar Tareo para Exportacion");
        mi_toNisiraAP.setEnabled(false);
        mi_toNisiraAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_toNisiraAPActionPerformed(evt);
            }
        });

        etiqueta2.setText("ERP Externo");

        swi_ennisira.setEnabled(false);
        swi_ennisira.setOnOff(false);

        lblModloLigero.setText("Modo Ligero");

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administracion de Tareos en el Servidor");
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        contenedor.setLayout(new java.awt.CardLayout());

        cardData.setBackground(new java.awt.Color(255, 255, 255));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaMousePressed(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        panelFilter.setOpaque(false);
        panelFilter.setLayout(new java.awt.CardLayout());

        panel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setOpaque(false);

        etiqueta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta3.setText("Este Mes");
        jPanel6.add(etiqueta3);

        swi_porEsteMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                swi_porEsteMesMouseClicked(evt);
            }
        });
        jPanel6.add(swi_porEsteMes);

        edt_usuario.setLabel("Ingresar Usuario");
        edt_usuario.setPreferredSize(new java.awt.Dimension(140, 36));
        jPanel6.add(edt_usuario);

        jLabel3.setPreferredSize(new java.awt.Dimension(12, 0));
        jPanel6.add(jLabel3);

        etiqueta5.setText("Fecha");
        jPanel6.add(etiqueta5);

        chooserDate1.setEnabled(false);
        chooserDate1.setPreferredSize(new java.awt.Dimension(120, 36));
        jPanel6.add(chooserDate1);

        etiqueta6.setText("Estado");
        jPanel6.add(etiqueta6);

        cbo_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PE", "PA", "VB", "AP", "AN", "NI", "RE", "OB", "CO", "DU" }));
        cbo_estado.setMaximumSize(new java.awt.Dimension(72, 34));
        cbo_estado.setMinimumSize(new java.awt.Dimension(72, 34));
        cbo_estado.setPreferredSize(new java.awt.Dimension(72, 34));
        cbo_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_estadoActionPerformed(evt);
            }
        });
        jPanel6.add(cbo_estado);

        jLabel2.setPreferredSize(new java.awt.Dimension(12, 0));
        jPanel6.add(jLabel2);

        etiqueta7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta7.setText("Planilla");
        jPanel6.add(etiqueta7);

        cboPlanillas.setPreferredSize(new java.awt.Dimension(220, 36));
        jPanel6.add(cboPlanillas);

        btnConsultar.setText("Consultar");
        btnConsultar.setPreferredSize(new java.awt.Dimension(120, 32));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        jPanel6.add(btnConsultar);

        panel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        panelFilter.add(panel1, "card2");

        javax.swing.GroupLayout cardDataLayout = new javax.swing.GroupLayout(cardData);
        cardData.setLayout(cardDataLayout);
        cardDataLayout.setHorizontalGroup(
            cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(panelFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE))
                .addContainerGap())
        );
        cardDataLayout.setVerticalGroup(
            cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(cardData, "cardData");

        cardGraphics.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cardGraphicsLayout = new javax.swing.GroupLayout(cardGraphics);
        cardGraphics.setLayout(cardGraphicsLayout);
        cardGraphicsLayout.setHorizontalGroup(
            cardGraphicsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1083, Short.MAX_VALUE)
        );
        cardGraphicsLayout.setVerticalGroup(
            cardGraphicsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        contenedor.add(cardGraphics, "cardFilter");

        cardDesconocidos.setBackground(new java.awt.Color(255, 255, 255));

        tablaUnknow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaUnknow);

        button1.setText("Consultar");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cardDesconocidosLayout = new javax.swing.GroupLayout(cardDesconocidos);
        cardDesconocidos.setLayout(cardDesconocidosLayout);
        cardDesconocidosLayout.setHorizontalGroup(
            cardDesconocidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDesconocidosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cardDesconocidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardDesconocidosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        cardDesconocidosLayout.setVerticalGroup(
            cardDesconocidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardDesconocidosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(cardDesconocidos, "cardDesconocidos");

        jPanel3.add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar1.setADJUNTAR(false);
        toolbar1.setCHART(false);
        toolbar1.setEDITAR(false);
        toolbar1.setEXCEL(true);
        toolbar1.setFILTRAR(true);
        toolbar1.setIREPORT(false);
        toolbar1.setRECARGAR(true);
        jPanel3.add(toolbar1, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cambioEstado(String idestado) {
        int[] rowSel = tabla.getSelectedRows();

        if (IDTAREO != null) {
            if (IDUSUARIO != null) {
                if (idestado.equals("OB") || idestado.equals("RE")) {
                    PDialogInputArea.PDialogShow(Frame, "Ingrese las Observaciones Respectivas por el estado: " + idestado,
                            () -> {
                                OBSERVACIONES = "El " + jkeys.IDUSUARIO + " esta " + (idestado.equals("RE") ? "Rechazando" : "Observando") + " el Tareo  por los siguientes motivos: " + PDialogInputArea.input;
                                applyCambioEstado(idestado);
                            },
                            () -> {
                                PrintMethods.printer("");
                            });
                } else {
                    applyCambioEstado(idestado);
                }
            } else {
                MaterialSmartDialog.show(Frame, MaterialSmartDialog.TIPE_DIALOG.INFORMATION, "", "No se ha seleccionado un Tareo");
            }
        } else {
            MaterialSmartDialog.show(Frame, MaterialSmartDialog.TIPE_DIALOG.INFORMATION, "", "No se ha seleccionado la Fila del Tareo");
        }
        OBSERVACIONES = "";
    }

    public void goEditar() {
        toolbar1.setEDITAR(false);

        MaterialSmartDialog.showConfirmation(Frame, "Esta seguro de Confirmar la siguiente Operacion",
                (JCallback) () -> {
                    editarTareo();
                },
                (JCallback) () -> {
                    PrintMethods.printer("");
                });
    }

    public void ActivarSupervisor() {
        switch (IDESTADO) {
            case "PE":
                mi_supervisor.setVisible(true);
                break;
            case "PA":
                mi_supervisor.setVisible(true);
                break;
            case "AP":
                mi_supervisor.setVisible(true);
                break;
            default:
                mi_supervisor.setVisible(false);
                break;
        }
        /*
        if(IDESTADO.equals("PE")){
            PrintMethods.printer("HOLAAAAAAAAAAA PE");
            mi_supervisor.setVisible(true);
            }else{
            PrintMethods.printer("HOLAAAAAAAAAAA 1");
                if(IDESTADO.equals("AP")){
                    PrintMethods.printer("HOLAAAAAAAAAAA 2");
                    mi_supervisor.setVisible(true);
                }else{
                    PrintMethods.printer("HOLAAAAAAAAAAA 3");
                        if(IDESTADO.equals("PA")){
                            PrintMethods.printer("HOLAAAAAAAAAAA 4");
                            mi_supervisor.setVisible(true);
                        }else{
                            PrintMethods.printer("HOLAAAAAAAAAAA 5");
                            mi_supervisor.setVisible(false);
                        }
                }
            }
        }*/
    }

    public void applyCambioEstado(String idestado) {
        int ROW_COUNT = tabla.getSelectedRowCount();
        PrintMethods.printer("TAREO QUE SERA CAMBIADO DE ESTADO: " + gmoEncript2022.encriptar(IDTAREO));
        if (ROW_COUNT == 1) {    
            ExecHTTP.sendApiDataSmart(
                frame,
                "api/desk/mano-de-obra/baja-cambio-estado2",
                "iddatos,iddocumento,idestado,observaciones,host,datainfo,idusuario",
                new Object[]{
                    IDTAREO,
                    "TAR",
                    idestado,
                    OBSERVACIONES,
                    INFO_HOST,
                    RunMain.INFO_HOST,
                    jkeys.IDUSUARIO
                },  (String s) -> {
                        SAVE_CALLBACK.action(frame);
                        Toast.mostrarSuccess(frame, "Se cambió el estado correctamente", false);
                    },
                () -> {
                    Toast.mostarError(frame, "Ocurrio un Incidente", false);
                });
        } else if (ROW_COUNT > 1) {
            String idtareo = JMethods.GetColumnSelect(tabla, 0);
            PrintMethods.printer("TAREO QUE SERA CAMBIADO DE ESTADO: " + gmoEncript2022.encriptar(idtareo));
            
            ExecHTTP.sendApiDataSmart(
                frame,
                "api/desk/mano-de-obra/baja-cambio-estado2",
                "iddatos,iddocumento,idestado,observaciones,host,datainfo,idusuario",
                new Object[]{
                    IDTAREO,
                    "TAR",
                    idestado,
                    OBSERVACIONES,
                    INFO_HOST,
                    RunMain.INFO_HOST,
                    jkeys.IDUSUARIO
                },  (String s) -> {
                        SAVE_CALLBACK.action(frame);
                        Toast.mostrarSuccess(frame, "Se cambió el estado correctamente", false);
                    },
                () -> {
                    Toast.mostarError(frame, "Ocurrio un Incidente", false);
                });
        }
    }

    public void applyCambioEstadobyToken(String idestado) {
        int ROW_COUNT = tabla.getSelectedRowCount();
        PrintMethods.printer("TAREO QUE SERA CAMBIADO DE ESTADO: " + gmoEncript2022.encriptar(IDTAREO));
        if (ROW_COUNT == 1) {
            ExecHTTP.ExecPostProcedure(Frame,
                    gettin_pages.api_set(),
                    new String[]{"iddatabase2", "query"},
                    new Object[]{
                        jkeys.IDDATABASE2,
                        ExecHTTP.parseQL("exec UpCambioEstado2021 ",
                                new Object[]{IDTAREO, "TAR", idestado, OBSERVACIONES, INFO_HOST, RunMain.INFO_HOST, TOKEN, jkeys.IDUSUARIO}
                        )
                    },
                    () -> {//ACTION DONE
                        gettin_data();
                    },
                    () -> {//ACTION WARN
                    }
            );
        } else if (ROW_COUNT > 1) {
            String idtareo = JMethods.GetColumnSelect(tabla, 0);
            PrintMethods.printer("TAREO QUE SERA CAMBIADO DE ESTADO: " + gmoEncript2022.encriptar(idtareo));
            ExecHTTP.ExecPostProcedure(Frame,
                    gettin_pages.api_set(),
                    new String[]{"iddatabase2", "query"},
                    new Object[]{
                        jkeys.IDDATABASE2,
                        ExecHTTP.parseQL("exec UpCambioEstado2021 ",
                                new Object[]{idtareo, "TAR", idestado, OBSERVACIONES, INFO_HOST, RunMain.INFO_HOST, TOKEN}
                        )
                    },
                    () -> {//ACTION DONE
                        gettin_data();
                    },
                    () -> {//ACTION WARN
                    });
        }
    }

    public void applyCambioSupervisor(String idsupervisorlinea) {
        int ROW_COUNT = tabla.getSelectedRowCount();
        PrintMethods.printer("TAREO QUE SERA CAMBIADO DE SUPERVISOR: " + gmoEncript2022.encriptar(IDTAREO));
        MaterialSmartDialog.showConfirmation(Frame, "Esta seguro de guardar los cambios???",
                (JCallback) () -> {

                    if (ROW_COUNT == 1) {
                        ExecHTTP.ExecPostProcedure(Frame,
                                gettin_pages.api_set(),
                                new String[]{"iddatabase2", "query"},
                                new Object[]{
                                    jkeys.IDDATABASE2,
                                    ExecHTTP.parseQL("exec UpCambioSupervisor2 ",
                                            new Object[]{IDTAREO, "TAR", idsupervisorlinea, OBSERVACIONES2, INFO_HOST, RunMain.INFO_HOST, jkeys.IDUSUARIO}
                                    //RunMain.HOST, RunMain.INFO_HOST}
                                    )
                                },
                                () -> {//ACTION DONE
                                    gettin_data();
                                },
                                () -> {//ACTION WARN
                                });

                    } else if (ROW_COUNT > 1) {
                        String idtareo = JMethods.GetColumnSelect(tabla, 0);
                        PrintMethods.printer("TAREO QUE SERA CAMBIADO DE SUPERVISOR: " + gmoEncript2022.encriptar(idtareo));
                        ExecHTTP.ExecPostProcedure(Frame,
                                gettin_pages.api_set(),
                                new String[]{"iddatabase2", "query"},
                                new Object[]{
                                    jkeys.IDDATABASE2,
                                    ExecHTTP.parseQL("exec UpCambioSupervisor ",
                                            new Object[]{idtareo, "TAR", idsupervisorlinea, OBSERVACIONES2, INFO_HOST, RunMain.INFO_HOST}
                                    //RunMain.HOST, RunMain.INFO_HOST}
                                    )
                                },
                                () -> {//ACTION DONE
                                    gettin_data();
                                },
                                () -> {//ACTION WARN
                                });
                    }
                },
                (JCallback) () -> {
                });
    }

    private void mi_aprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_aprobarActionPerformed

//        JDialog.setDefaultLookAndFeelDecorated(false);
//
//        AprobarTareoToken OBJ = new AprobarTareoToken((java.awt.Frame) Frame, true, jkeys.IDUSUARIO);
//        OBJ.setSize(300, 200);
//        OBJ.setCallbackFrame((Window frame1) -> {
//            this.TOKEN = OBJ.getTOKEN();
//            PrintMethods.printer("TOKEEEEEEEEEEEEEEEEEEEEEEN: " + TOKEN);
//            OBJ.dispose();
        cambioEstado("AP");
//        });
//        JMethods.settingGlassPane((JFrame) Frame, OBJ, defaults.colorPrimary, 0.6f);
//        JDialog.setDefaultLookAndFeelDecorated(true);
    }//GEN-LAST:event_mi_aprobarActionPerformed

    private void applyMouseClicked(MouseEvent evt) {
        OBSERVACIONES2 = "";
        if (tabla.getRowCount() > 0) {
            int f = tabla.getSelectedRow();
            IDTAREO = tabla.getValueAt(f, 0).toString();
            FECHAPROGRAMACION = tabla.getValueAt(f, 12).toString();
            IDUSUARIO = tabla.getValueAt(f, 1).toString();
            DOCUMENTO = tabla.getValueAt(f, 7).toString();
            IDPLANILLA = tabla.getValueAt(f, 6).toString();
            IDTURNO = tabla.getValueAt(f, 10).toString();
            IDESTADO = tabla.getValueAt(f, 9).toString();
            TRAZABILIDAD = tabla.getValueAt(f, 14).toString();
            DETALLE = tabla.getValueAt(f, 15).toString();
            HORASE = tabla.getValueAt(f, 16).toString();
            HORASB = tabla.getValueAt(f, 17).toString();
            TIPOTAREO = tabla.getValueAt(f, 18).toString();

            if (PUEDE_EDITAR) {
                executrePrivilegiostoExportNisira(IDESTADO);
                if (tabla.getValueAt(f, 12).equals("CON ASIST")) {
                    mi_generarAsistencia.setEnabled(false);
                } else if (tabla.getValueAt(f, 12).equals("SIN ASIST")) {
                    mi_generarAsistencia.setEnabled(true);
                }

            }
        }
        ActivarSupervisor();
    }

    private void mi_observacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_observacionesActionPerformed
        String obs = tabla.getValueAt(tabla.getSelectedRow(), 11).toString().split("_;_")[0];
        Message.showMessage(this, "Observaciones del Tareo", obs);
    }//GEN-LAST:event_mi_observacionesActionPerformed

    private void mi_rechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_rechazarActionPerformed
        cambioEstado("RE");
    }//GEN-LAST:event_mi_rechazarActionPerformed

    private void mi_observarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_observarActionPerformed
        cambioEstado("OB");
    }//GEN-LAST:event_mi_observarActionPerformed

    private void mi_generarAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_generarAsistenciaActionPerformed
        if (IDTAREO != null) {

            MaterialSmartDialog.showConfirmation(Frame, "Esta seguro de Generar Asistencia???",
                    (JCallback) () -> {
                        JDialog.setDefaultLookAndFeelDecorated(false);
                        GenerarAsistencia D = new GenerarAsistencia((JFrame) Frame, true, IDTAREO);
                        JMethods.settingGlassPane((JFrame) Frame, D, MaterialColor.BLUEGREY_400, 0.5f);
                        JDialog.setDefaultLookAndFeelDecorated(true);
                    },
                    (JCallback) () -> {
                    });
        } else {
            MaterialSmartDialog.show(Frame, MaterialSmartDialog.TIPE_DIALOG.INFORMATION, "", "Seleccione un Tareo");
        }
    }//GEN-LAST:event_mi_generarAsistenciaActionPerformed

    private void mi_toNisiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_toNisiraActionPerformed
        MaterialSmartDialog.showConfirmation(Frame, "Esta seguro de Enviar a ERP Externo los Tareos Seleccionados???",
                (JCallback) () -> {
                    if (tabla.getSelectedRowCount() > 1) {
                        String idtareos = JMethods.GetColumnSelect(tabla, 0);
                        JDialog.setDefaultLookAndFeelDecorated(false);
                        SendNisira D = new SendNisira((JFrame) Frame, true, idtareos, this::gettin_data);
                        JMethods.settingGlassPane((JFrame) Frame, D, MaterialColor.BLUEGREY_400, 0.8f);
                        JDialog.setDefaultLookAndFeelDecorated(true);

                    } else {

                        ExecHTTP.ExecPostProcedure(Frame,
                                gettin_pages.api_set(),
                                new String[]{"iddatabase2", "query"},
                                new Object[]{
                                    jkeys.IDDATABASE2,
                                    ExecHTTP.parseQL("exec SetTareoExport  ",
                                            //                                            "exec SetTareoExterno ",
                                            IDTAREO,
                                            FECHAPROGRAMACION,
                                            IDUSUARIO,
                                            jkeys.IDDATABASE,
                                            IDPLANILLA,
                                            IDTURNO,
                                            INFO_HOST,
                                            INFO_HOST
                                    )
                                },
                                () -> {//ACTION DONE
                                    gettin_data();
                                },
                                () -> {//ACTION WARN
                                });
                    }

                },
                (JCallback) () -> {
                    PrintMethods.printer("");
                });
    }//GEN-LAST:event_mi_toNisiraActionPerformed

    private void mi_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_detalleActionPerformed
        goDetalle();
    }//GEN-LAST:event_mi_detalleActionPerformed


    private void mi_eliminarTareoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_eliminarTareoActionPerformed
        ExecHTTP.ExecPostProcedure(Frame,
                gettin_pages.api_set(),
                new String[]{"iddatabase2", "query"},
                new Object[]{
                    jkeys.IDDATABASE2,
                    ExecHTTP.parseQL("exec SetDropTareo ",
                            IDTAREO, VALUE
                    )
                },
                () -> {//ACTION DONE
                    gettin_data();
                },
                () -> {//ACTION WARN
                });
    }//GEN-LAST:event_mi_eliminarTareoActionPerformed

    private void mi_editarTareoOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_editarTareoOnlineActionPerformed

    }//GEN-LAST:event_mi_editarTareoOnlineActionPerformed

    private void mi_detalleconsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_detalleconsActionPerformed
        if (tabla.getSelectedRowCount() > 0) {
            IDTAREO = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            DETALLE = tabla.getValueAt(tabla.getSelectedRow(), 13).toString();

            IDTAREO = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
            
            JDialog.setDefaultLookAndFeelDecorated(false);
            load = new SmartLoader((java.awt.Frame) Frame, true,
                    "Descargando Datos del Personal SubTareado",
                    "Se estan Descargando los datos de los subtareos",
                    (Window frame) -> {                    
                        tabla.loadApiDataSmart(
                                            "api/desk/mano-de-obra/consumidor-por-tareo",
                                            "idtareo",
                                            IDTAREO
                                    );                   
                        load.dispose();
                        JDialog.setDefaultLookAndFeelDecorated(true);
                    });

            JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
            load = null;
            JMethods.updateInternalJTable(this, tabla);

        } else {
            Toast.mostarInfo((JFrame) Frame, "Seleccione un Tareo", true);
        }


    }//GEN-LAST:event_mi_detalleconsActionPerformed

    private void tablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaMousePressed

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_D) {
            goDetalle();
        }
    }//GEN-LAST:event_tablaKeyPressed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        applyMouseClicked(evt);
    }//GEN-LAST:event_tablaMouseClicked

    private void mi_toNisiraDuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_toNisiraDuActionPerformed
        panDuplicarTareo p = new panDuplicarTareo(Frame, true, "Diplicar Tareo", false, IDPLANILLA, IDTAREO);
        p.mostrar();
    }//GEN-LAST:event_mi_toNisiraDuActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        MaterialSmartDialog.showConfirmation(Frame, "Esta seguro de Consultar los Tareos Filtrados",
                (JCallback) () -> {
                    gettin_data();
                },
                (JCallback) () -> {
                    PrintMethods.printer("");
                });

    }//GEN-LAST:event_btnConsultarActionPerformed

    private void swi_porEsteMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swi_porEsteMesMouseClicked
        chooserDate1.setEnabled(!swi_porEsteMes.isOnOff());
        if (!jkeys.RUC.equals("20554556192"))
            validateConsolidado();
    }//GEN-LAST:event_swi_porEsteMesMouseClicked

    private void btnDesconocidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconocidosActionPerformed
        panelShow = "cardDesconocidos";
        CARDLAYOUT.show(contenedor, panelShow);
        toolbar1.setRECARGAR(true);
        toolbar1.setFILTRAR(false);
        toolbar1.setEXCEL(true);
        toolbar1.setREGRESAR(true);
        btnDesconocidos.setVisible(false);
    }//GEN-LAST:event_btnDesconocidosActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        getDesconocidos();
    }//GEN-LAST:event_button1ActionPerformed

    private void mi_toNisiraAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_toNisiraAPActionPerformed
        MaterialSmartDialog.showConfirmation(Frame, "Esta seguro de Aprobar estos Tareos, esto indica que usted da su visto bueno para enviarlos al Procesamiento de Planilla ???",
                (JCallback) () -> {
                    if (tabla.getSelectedRowCount() > 1) {
                        int selTar[] = tabla.getSelectedRows();
                        String idtareos = "";
                        for (int i = 0; i < selTar.length - 1; i++) {//3
                            idtareos = idtareos + tabla.getValueAt(selTar[i], 0).toString() + ",";
                        }
                        idtareos = idtareos + tabla.getValueAt(selTar[selTar.length - 1], 0).toString();

                        JDialog.setDefaultLookAndFeelDecorated(false);
                        SendNisira D = new SendNisira((JFrame) Frame, true, idtareos, this::gettin_data);
                        JMethods.settingGlassPane((JFrame) Frame, D, MaterialColor.BLUEGREY_400, 0.8f);
                        JDialog.setDefaultLookAndFeelDecorated(true);

                    } else {

                        ExecHTTP.ExecPostProcedure(Frame,
                                gettin_pages.api_set(),
                                new String[]{"iddatabase2", "query"},
                                new Object[]{
                                    jkeys.IDDATABASE2,
                                    ExecHTTP.parseQL("exec setApParaExportar  ",
                                            IDTAREO,
                                            FECHAPROGRAMACION,
                                            IDUSUARIO,
                                            jkeys.IDDATABASE,
                                            IDPLANILLA,
                                            IDTURNO,
                                            INFO_HOST, RunMain.INFO_HOST
                                    )
                                },
                                () -> {//ACTION DONE
                                    gettin_data();
                                },
                                () -> {//ACTION WARN
                                });
                    }

                },
                (JCallback) () -> {
                    PrintMethods.printer("");
                });
    }//GEN-LAST:event_mi_toNisiraAPActionPerformed

    private void mi_aprobarpeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_aprobarpeActionPerformed
        switch (jvalues.USUARIO.getTipousuario().getIdtipousuario()) {

            case "SUPERRRHH":
            case "SUPERRRHHPACK":
            case "COORDRRHHPACK":
            case "JEFERRHH":
            case "HTECUSER":
            case "ADMINIST":
                cambioEstado("PE");
                break;
            case "ANALYSTRRHHPACK":
            case "ANALYSTRRHH":
                aprobarPendienteAtrasado();
                break;
            default:
                Toast.mostarInfo((JFrame) Frame, "No existen Movimientos para su Perfil", true);
                break;
        }
    }//GEN-LAST:event_mi_aprobarpeActionPerformed


    private void cbo_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_estadoActionPerformed
        IDESTADO = cbo_estado.getSelectedItemString();
        executrePrivilegiostoExportNisira(IDESTADO);
    }//GEN-LAST:event_cbo_estadoActionPerformed

    private void mi_supervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_supervisorActionPerformed
        updateSupervisor();
    }//GEN-LAST:event_mi_supervisorActionPerformed

    public void editarTareo() {
        if (editTareo == null || editTareo.isClosed()) {
            try {
                this.editTareo = new AdminSubTareos((java.awt.Frame) Frame, IDTAREO, IDUSUARIO, IDPLANILLA, swi_ennisira.isOnOff(), cbo_estado.getSelectedItemString());
                this.getDesktopPane().add(this.editTareo);
                this.editTareo.setVisible(true);
                this.editTareo.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ExecHTTP.showException(ex, Frame, () -> {
                    System.exit(0);
                }, "Salir del Sistema");
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button btnConsultar;
    private kevin.component.button.ButtonMaterialIcon btnDesconocidos;
    private kevin.component.button.Button button1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel cardData;
    private kevin.component.panel.Panel cardDesconocidos;
    private javax.swing.JPanel cardGraphics;
    private kevin.component.combobox.ComboBox cboPlanillas;
    private kevin.component.combobox.ComboBox cbo_estado;
    private kevin.component.date.MaterialDateChooser chooserDate1;
    private javax.swing.JPanel contenedor;
    private kevin.component.edittext.EditText edt_usuario;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta5;
    private kevin.component.label.Etiqueta etiqueta6;
    private kevin.component.label.Etiqueta etiqueta7;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private kevin.component.label.Etiqueta lblModloLigero;
    private javax.swing.JMenuItem mi_aprobar;
    private kevin.component.menu.GMOMenuItem mi_aprobarpe;
    private javax.swing.JMenuItem mi_detalle;
    private javax.swing.JMenuItem mi_detallecons;
    private javax.swing.JMenuItem mi_editarTareoOnline;
    private javax.swing.JMenuItem mi_eliminarTareo;
    private javax.swing.JMenuItem mi_generarAsistencia;
    private javax.swing.JMenuItem mi_observaciones;
    private javax.swing.JMenuItem mi_observar;
    private javax.swing.JMenuItem mi_rechazar;
    private javax.swing.JMenuItem mi_supervisor;
    private javax.swing.JMenuItem mi_toNisira;
    private javax.swing.JMenuItem mi_toNisiraAP;
    private javax.swing.JMenuItem mi_toNisiraDu;
    private javax.swing.JMenu mn_admin;
    private javax.swing.JMenu mn_resumen;
    private kevin.component.panel.Panel panel1;
    private javax.swing.JPanel panelFilter;
    private javax.swing.JPopupMenu popup;
    private javax.swing.JPopupMenu popupNisira;
    private kevin.component.switchbox.SwitchBox swi_ennisira;
    private kevin.component.switchbox.SwitchBox swi_modoLigero;
    private kevin.component.switchbox.SwitchBox swi_porEsteMes;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.tabla.TablaSmart tablaUnknow;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables

    class FormatAdminTareosOnline extends DefaultTableCellRenderer {

        public FormatAdminTareosOnline() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled());
            switch (column) {
                case 6:
                    if (table.getValueAt(row, column).toString().contains("!EC")) {
                        setBackground(MaterialColor.PURPLE_200);
                        setForeground(MaterialColor.WHITE);
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person_stop.png")));
                        setFont(defaults.getFontBoldItalic(15));
                    } else {
                        setIcon(null);
                        setBackground(Color.WHITE);
                        setForeground(MaterialColor.GREY_800);
                    }
                    break;
                case 10:
                    if (table.getValueAt(row, column).toString().contains("01")) {
                        setForeground(MaterialColor.WHITE);
                        setBackground(MaterialColor.WHITE);
                        setHorizontalAlignment(CENTER);
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/turno_diurno.png")));
                    } else {
                        setForeground(MaterialColor.WHITE);
                        setBackground(MaterialColor.WHITE);
                        setHorizontalAlignment(CENTER);
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/turno_nocturno.png")));
                    }
                    break;
                case 8:
                    if (table.getValueAt(row, column).toString().toLowerCase().contains("(")) {
                        setBackground(MaterialColor.RED_100);
                        setForeground(MaterialColor.RED_900);
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person_register.png")));
                        setFont(defaults.getFontBoldItalic(15));
                    } else {
                        setIcon(null);
                        setBackground(Color.WHITE);
                        setForeground(MaterialColor.GREY_800);
                    }
                    break;
                case 9:
                    switch (value.toString()) {
                        case "PE":
                            setIcon(null);
                            setBackground(MaterialColor.AMBER_300);
                            setForeground(MaterialColor.BLACK);
                            break;
                        case "AP":
                            setIcon(null);
                            setBackground(MaterialColor.INDIGO_600);
                            setForeground(MaterialColor.WHITE);
                            break;
                        case "NI":
                            setIcon(null);
                            setBackground(MaterialColor.LIGHTBLUE_400);
                            setForeground(MaterialColor.BLACK);
                            break;
                        case "RE":
                            setIcon(null);
                            setBackground(MaterialColor.RED_400);
                            setForeground(MaterialColor.WHITE);
                            break;
                        case "AN":
                            setIcon(null);
                            setBackground(MaterialColor.GREY_700);
                            setForeground(MaterialColor.WHITE);
                            break;
                        case "DU":
                            setIcon(null);
                            setBackground(MaterialColor.ORANGE_600);
                            setForeground(MaterialColor.WHITE);
                            break;
                        default:
                            break;
                    }
                    break;
                case 15:
                    if (value.toString().equals("0")) {
                        setIcon(null);
                        setBackground(Color.WHITE);
                        setForeground(Color.WHITE);
                    } else {
                        setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.PAN_TOOL, jmugi.component.MaterialColor.WHITE, 20f));
                        setBackground(MaterialColor.RED_500);
                        setForeground(MaterialColor.RED_500);
                    }
                    break;
                case 17:
                    switch (value.toString()) {
                        case "0":
                            setIcon(null);
                            setBackground(MaterialColor.WHITE);
                            setForeground(Color.WHITE);
                            break;
                        case "1":
                            setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ico_iscosecha.png")));
                            setForeground(Color.WHITE);
                            setBackground(MaterialColor.WHITE);
                            break;
                        case "2":
                            setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ico_isnocosecha.png")));
                            setForeground(Color.WHITE);
                            setBackground(MaterialColor.WHITE);
                            break;
                        default:
                            setIcon(null);
                            setBackground(Color.WHITE);
                            setForeground(MaterialColor.WHITE);
                            break;
                    }
                    break;
                case 18:
                    if (table.getValueAt(row, column).equals("C")) {
                        setForeground(Color.WHITE);
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icoCampo.png")));
                    } else if (table.getValueAt(row, column).equals("P")) {
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icoPacking.png")));
                        setForeground(Color.WHITE);
                    }
                    break;
                default:
                    setHorizontalAlignment(LEFT);
                    setIcon(null);
                    setBackground(Color.WHITE);
                    setForeground(MaterialColor.GREY_800);
            }

            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            return this;
        }
    }

}
