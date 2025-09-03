<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Usuario incorrecto</title>
  <style>
    body {
      margin: 0;
      font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(135deg, #1976d2, #d81b60);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: #333;
    }
    .card {
      background: #fff;
      border-radius: 16px;
      padding: 28px 24px;
      max-width: 400px;
      text-align: center;
      box-shadow: 0 10px 20px rgba(0, 0, 0, 0.25);
      position: relative;
      overflow: hidden;
    }
    h1 {
      color: #d32f2f;
      font-size: 1.6rem;
      margin-bottom: 16px;
    }
    p {
      margin-top: 8px;
      font-size: 0.95rem;
      color: #555;
    }
    .actions {
      margin-top: 18px;
      display: flex;
      gap: 12px;
      justify-content: center;
      flex-wrap: wrap;
    }
    .btn {
      text-decoration: none;
      border: none;
      padding: 10px 16px;
      border-radius: 8px;
      cursor: pointer;
      font-weight: bold;
      transition: transform 0.2s ease, background 0.3s ease;
    }
    .btn-primary {
      background: #1976d2;
      color: white;
    }
    .btn-primary:hover {
      background: #125ca1;
      transform: scale(1.05);
    }
    .btn-ghost {
      background: transparent;
      color: #1976d2;
      border: 2px solid #1976d2;
    }
    .btn-ghost:hover {
      background: #1976d2;
      color: white;
      transform: scale(1.05);
    }
    .hint {
      margin-top: 12px;
      font-size: 0.85rem;
      color: #999;
    }
    /* Video estilizado */
    iframe {
      width: 100%;
      max-width: 320px;
      height: 180px;
      margin: 14px auto;
      border-radius: 12px;
      box-shadow: 0 6px 12px rgba(0,0,0,0.2);
      display: block;
    }
    /* Efecto pulsante detrás */
    .pulse {
      position: absolute;
      top: -50%;
      left: -50%;
      width: 200%;
      height: 200%;
      background: rgba(216, 27, 96, 0.1);
      border-radius: 50%;
      animation: pulse 6s infinite;
      z-index: -1;
    }
    @keyframes pulse {
      0% { transform: scale(0.9); opacity: 0.7; }
      50% { transform: scale(1.1); opacity: 0.3; }
      100% { transform: scale(0.9); opacity: 0.7; }
    }
  </style>
</head>
<body>
  <main class="card" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="pulse" aria-hidden="true"></div>
    
    <h1>Usuario incorrecto</h1>

    <!-- YouTube Short embebido con autoplay -->
    <iframe
      src="https://www.youtube.com/embed/DD4AzD9nolE?autoplay=1&mute=1&loop=1&playlist=DD4AzD9nolE"
      title="YouTube Short"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
      allowfullscreen>
    </iframe>

    <p>Verifica tu usuario y vuelve a intentarlo. Si olvidaste tus datos, usa la opción de recuperación.</p>

    <div class="actions">
      <a class="btn btn-primary" href="loguin.jsp">🔄 Volver a intentarlo</a>
      <button class="btn btn-ghost" type="button" onclick="history.back()">⬅ Volver</button>
    </div>

    <p class="hint">Código de error: AUTH-401</p>
  </main>
</body>
</html>
    