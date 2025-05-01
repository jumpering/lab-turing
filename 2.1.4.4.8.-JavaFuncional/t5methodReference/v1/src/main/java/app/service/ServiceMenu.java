package app.service;

import util.view.menu.IterativeMenu;
import util.values.Date;
import util.values.Interval;
import util.values.Time;
import util.collection.Iterator;
import util.collection.map.LinkedMap;
import util.collection.set.LinkedSet;

public class ServiceMenu extends IterativeMenu {

  public ServiceMenu(LinkedMap<Date, Interval<Time>> target) {
    super("Menú de Servicio");
    this.add("Listar", new ServiceOption(target) {

      public void interact() {
        LinkedSet<Date> set = this.getTarget().keySet();
        Iterator<Date> iterator = set.iterator();
        while (iterator.hasNext()) {
          Date date = iterator.next();
          new ServiceDialog().writeln(new Service(date, this.getTarget().get(date)));
        }
      }

    });
    this.add("Añadir", new ServiceOption(target) {

      public void interact() {
        ServiceDialog serviceDialog = new ServiceDialog("Nuevo Servicio a registrar: \r\n" + //
                    "[   Formato correcto de entrada:  ]\r\n" + //
                    "----------------------------------\r\n" + //
                    "Fecha  -> aaaa/m/d  (Ej: 2025/7/1)\r\n" + //
                    "Tiempo -> HH:mm:ss  (Ej: [22:30:0,23:30:0])\r\n" + //
                    "Separador entre fecha y tiempo: \"|\"\r\n" + //
                    "----------------------------------\r\n" + //
                    "Ejemplo de entrada correcta:\r\n" + //
                    "<2025/7/1|[22:30:0,23:30:0]>\r\n" + //
                    "como se ve perfectamente en esta expresión regular:\r\n ");
        Service service = (Service) serviceDialog.read();
        this.getTarget().put(service.getKey(), service.getValue());
      }

    });
    this.add("Menu de Borrado de Servicio", new RemovingServiceMenu(target));
  }

}
