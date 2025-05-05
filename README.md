# 📝 Demo

---

## Запуск на Windows

### 1. Установить [Docker Desktop](https://www.docker.com/products/docker-desktop)

  ```bash
  docker --version
  docker-compose version
  ```

---

### 2. Клонировать проект и перейти в папку /docker

---

### 3. Запустить проект

```bash
docker-compose up --build
```

---

### 4. Контейнеры:

- URL сервера: [http://localhost:8080](http://localhost:8080)
- Профиль по умолчанию: 'jpa'
- Консоль H2 доступна по адресу: [http://localhost:81](http://localhost:81) (имя пользователя и пароль: 'test')