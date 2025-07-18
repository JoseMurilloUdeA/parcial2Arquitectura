# Usamos una imagen ligera de nginx para servir contenido estático
FROM nginx:alpine

# Elimina configuración por defecto de nginx y copia nuestra propia config
COPY default.conf /etc/nginx/conf.d/default.conf

# Copiamos el contenido de nuestro frontend
COPY . /usr/share/nginx/html

# Exponemos el puerto 80 (servido por nginx)
EXPOSE 80
