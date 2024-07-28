FROM node:16.20-alpine as build
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
RUN npm run build

FROM nginxinc/nginx-unprivileged:1.25.4-alpine
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/newssearch_ui /usr/share/nginx/html
EXPOSE 8000
CMD ["nginx", "-g", "daemon off;"]