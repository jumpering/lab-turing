package app.menu;

import java.util.stream.IntStream;

import util.view.dialog.primitive.Console;
import util.view.menu.IterativeMenu;

class ModelIterativeMenu extends IterativeMenu {

    public ModelIterativeMenu(Model model) {
        super("Model Iterative Menu");
        this.add("Listar", () -> {
                IntStream.range(0,model.size())
                .mapToObj((i) -> (i + 1) + ". " + model.get(i)).
                forEach(Console.instance()::writeln);

                Console.instance().writeln();
            });
        this.add("Listar inverso", () -> {
                IntStream.rangeClosed( model.size() - 1,0)
                .mapToObj((i) -> (i + 1) + ". " + model.get(i))
                .forEach(Console.instance()::writeln);
                Console.instance().writeln();
            });
        this.add("Buscar", () -> {
                String string;
                boolean ok;
                do {
                    string = Console.instance().readString("Dame una cadena de caracteres: ").trim();
                    ok = !string.equals("");
                    if (!ok) {
                        Console.instance().writeln("Error! Introduce algún caracter");
                    }
                } while (!ok);
                Console.instance().writeln((model.find(string) ? "Si" : "No") + " se encuentra");
            });
        this.add("Añadir", () -> {
                String string;
                do {
                    string = Console.instance().readString("Dame una cadena de caracteres: ");
                } while (string.trim().equals(""));
                model.add(string.trim());
            });
        this.add("Menu Eliminar", new ModelDynamicMenu(model));
    }
    
}
