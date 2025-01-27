FROM nginx:alpine

# Copy the built frontend files to the nginx server
COPY /dist/frontend/browser /usr/share/nginx/html

# Copy the nginx configuration file to the nginx server
COPY nginx.conf /etc/nginx/templates/default.conf.template

# Expose the port 80
EXPOSE 8080

# Start the nginx server
CMD ["nginx", "-g", "daemon off;"]
