import java.util.Scanner;

import data.DataCursos;
import entities.Cursos;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        DataCursos data = new DataCursos();
        Scanner input = new Scanner(System.in);
        int opt = 0;

        do {
            System.out.println("***** CRUD CURSOS *****");
            System.out.println("1 Lista de cursos ");
            System.out.println("2 Nuevo curso ");
            System.out.println("3 Elimar curso ");
            System.out.println("4 Curso por ID ");
            System.out.println("5 Actualizar Curso ");
            System.out.println("0 Salir ");
            System.out.println("Eligue una opcion: ");
            opt = input.nextInt();
            System.out.println("Elegiste: " + opt);
            input.nextLine(); // Limpiar el buffer
            switch (opt) {
                case 1:
                    System.out.println("Lista de Cursos ");
                    for (Cursos d : data.list("")) {
                        System.out.println(d.getId() + "\t" + d.getNombre() + "\t" + d.getDescripcion() + "\t" + d.getClase() + "\t" + d.getCreditos());
                    }
                    break;
                case 2:
                    System.out.println("Nueva Curso: ");
                    Cursos c = new Cursos();
                    System.out.print("Nombre: ");
                    c.setNombre(input.nextLine());
                    System.out.print("Descripcion: ");
                    c.setDescripcion(input.nextLine());
                    System.out.print("Clase: ");
                    c.setClase(input.nextLine());

                    System.out.print("Creditos: ");
                    try {
                        c.setCreditos(input.nextInt());
                        data.crear(c);
                    } catch (Exception e) {
                        input.nextLine(); // Limpiar el buffer
                        System.out.print("Creditos debe ser entero, no se guardo");
                    }

                    break;
                case 3:
                    System.out.println("Eliminar Curso ");
                    System.out.print("id: ");
                    data.delete(input.nextInt());
                    break;
                case 4:
                    int b4 = 1;
                    do {
                        System.out.println("Obetener Curso");
                        System.out.print("id: ");
                        int id = 0;
                        try {
                            b4 = 0;
                            id = input.nextInt();
                            Cursos d = data.get(id);
                            if (d != null) {
                                System.out.println("Id: " + d.getId());
                                System.out.println("Nombre: " + d.getNombre());
                            } else {
                                System.out.print("El curso no existe");
                            }
                        } catch (Exception e) {
                            input.nextLine(); // Limpiar el buffer
                            System.out.print("id no valido, debe ser un numero");
                            b4 = 1;
                        }

                    } while (b4 != 0);

                    break;
                case 5:
                    System.out.println("Actualizar Curso ");
                    System.out.print("id: ");

                    Cursos cur = data.get(input.nextInt());

                    if (cur != null) {
                        System.out.println("Nombre anterior: " + cur.getNombre());
                        System.out.println("Descripcion anterior: " + cur.getDescripcion());
                        System.out.println("Clase anterior: " + cur.getClase());
                        System.out.println("Creditos anterior: " + cur.getCreditos());

                        input.nextLine(); // Limpiar el buffer
                        System.out.print("Nuevo Nombre: ");
                        cur.setNombre(input.nextLine());

                        System.out.print("nuevo Descripcion: ");
                        cur.setDescripcion(input.nextLine());

                        System.out.print("Nueva Clase: ");
                        cur.setClase(input.nextLine());

                        System.out.print("Nuevo Creditos: ");
                        try {
                            cur.setCreditos(input.nextInt());
                            data.update(cur);
                        } catch (Exception e) {
                            // pro.setPrecio(0);
                            input.nextLine(); // Limpiar el buffer
                            System.out.print("Los creditos debe ser entero, no se guardo");
                        }

                    } else {
                        System.out.println("NO encontrado");
                    }

                    break;
                case 0:
                    System.out.println("Saliendo del programa");
                    input.close();
                    return;

                default:
                    System.out.println("Opcion no valida");
            }
        } while (opt != 0);
    }
}