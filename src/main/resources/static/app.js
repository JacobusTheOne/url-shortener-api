const API_BASE = "http://localhost:8080";

function login() {
  fetch(`${API_BASE}/api/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      email: document.getElementById("email").value,
      password: document.getElementById("password").value
    })
  })
  .then(res => {
    if (!res.ok) throw new Error("Login failed");
    return res.json();
  })
  .then(data => {
    localStorage.setItem("token", data.token);
    window.location.href = "app.html";
  })
  .catch(() => {
    document.getElementById("error").innerText = "Invalid credentials";
  });
}
function register() {
  fetch(`${API_BASE}/api/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      email: document.getElementById("email").value,
      password: document.getElementById("password").value
    })
  })
  .then(res => {
    if (!res.ok) throw new Error("Registration failed");
    return res.text();
  })
  .then(() => {
    document.getElementById("message").innerText =
      "Registration successful. You can now log in.";
    setTimeout(() => {
      window.location.href = "login.html";
    }, 1500);
  })
  .catch(() => {
    document.getElementById("message").innerText =
      "Registration failed. User may already exist.";
  });
}

function shorten() {
  const token = localStorage.getItem("token");

  fetch(`${API_BASE}/api/urls`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + token
    },
    body: JSON.stringify({
      originalUrl: document.getElementById("originalUrl").value
    })
  })
  .then(res => {
    if (!res.ok) throw new Error("Unauthorized");
    return res.json();
  })
  .then(data => {
    const shortUrl = `${API_BASE}/${data.shortCode}`;
    document.getElementById("result").innerHTML =
      `<a href="${shortUrl}" target="_blank">${shortUrl}</a>`;
  })
  .catch(() => {
    document.getElementById("result").innerText = "Error creating URL";
  });
}
