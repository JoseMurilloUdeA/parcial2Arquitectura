const API_BASE_URL = "http://localhost:8081"; // <--- Reemplázala con tu URL real cuando esté disponible

document.getElementById("productoForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const nombre = document.getElementById("nombre").value;
    const descripcion = document.getElementById("descripcion").value;
    const precio = parseFloat(document.getElementById("precio").value);
    const cantidad = parseInt(document.getElementById("cantidad").value);
    const sede = document.getElementById("sede").value;

    const data = {
        name: nombre,
        description: descripcion,
        price: precio,
        initial_amout: cantidad
    };

    fetch(`${API_BASE_URL}/api/v1/storage/${sede}/products`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "X-API-Version": "v1"
        },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) throw new Error("Error al guardar producto");
        return res.json();
    })
    .then(data => {
        alert("Producto guardado correctamente");
        document.getElementById("productoForm").reset();
    })
    .catch(err => {
        console.error("Error:", err);
        alert("Ocurrió un error al guardar el producto");
    });
});


function consultarInventario() {
    const sede = document.getElementById("consultaSede").value;

    if (!sede) {
        alert("Por favor, ingresa un ID de sede.");
        return;
    }

    fetch(`${API_BASE_URL}/api/v1/storage/${sede}/products`, {
        headers: {
            "X-API-Version": "v1"
        }
    })
    .then(res => {
        if (!res.ok) throw new Error("Error al consultar inventario");
        return res.json();
    })
    .then(data => {
        const resultadoDiv = document.getElementById("resultadoInventario");
        resultadoDiv.innerHTML = "<h3>Productos encontrados:</h3>";

        if (Array.isArray(data) && data.length > 0) {
            const lista = document.createElement("ul");
            data.forEach(prod => {
                const item = document.createElement("li");
                item.textContent = `${prod.name} - ${prod.initial_amout} unidades - $${prod.price}`;
                lista.appendChild(item);
            });
            resultadoDiv.appendChild(lista);
        } else {
            resultadoDiv.innerHTML += "<p>No se encontraron productos para esta sede.</p>";
        }
    })
    .catch(err => {
        console.error("Error:", err);
        alert("Error al consultar inventario");
    });
}
