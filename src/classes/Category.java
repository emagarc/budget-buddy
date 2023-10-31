package classes;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Método para convertir el nombre de la categoría a minúsculas
    public void toLowerCase() {
        this.name = this.name.toLowerCase();
    }

    // Sobrescribe el método equals para comparación sin distinción entre mayúsculas y minúsculas
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Category category = (Category) obj;

        return name.equalsIgnoreCase(category.name);
    }

    // Sobrescribe el método hashCode para garantizar que dos objetos iguales tengan el mismo hash
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}
