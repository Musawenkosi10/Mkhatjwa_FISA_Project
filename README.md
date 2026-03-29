# 🚨 Mkhatjwa Final Year FISA Project

Java-based Distributed Security Monitoring System
Part of my Software Development 2 (SFD2) Final Integrated Student Assessment (FISA).

This project simulates a control room environment where multiple client applications communicate with a central server to manage home security alerts in real time.

---

## 📌 Project Overview

The system consists of three Java applications, each serving a unique role:

- **Control Room Server** - Central server coordinating all alarms and client connections.
- **Client Guard Application** - Simulates a security guard responding to alarms and updating status.
- **Client Home Application** - Home-side client interface for triggering alarms and sending location info.

The system demonstrates real-time client-server communication, alarm handling and guard dispatch workflow.

---

## 🧩 System Architecture

- Follows a client–server model.
- Server manages and coordinates client connections.
- Clients communicate via sockets, exchanging alarm and status data.

Key Concepts Implemented:

- ✅ Socket Programming
- ✅ Multi-application Communication
- ✅ Distributed Systems
- ✅ Event-driven GUI in Java (Swing & JavaFX)

---

## 🖥️ Applications Included

### 🔹 Control Room Server
- Accepts client connections (Home and Guard).
- Processes alarms and manages guard dispatch decisions.
- Displays alerts and logs guard actions.

---

### 🔹 Client Guard
- Swing GUI simulating guard workflow.
- Updates server on:
1. On my way
2. Arrived
3. House safe / Send backup
- Receives alarm addresses from the server.

---

### 🔹 Client Home
- JavaFX GUI for home clients.
- Alarm armed/disarmed with code sequence (132).
- Sends location and alarm status to server when triggered.

---

## ⚙️ Technologies Used

- **Java**
- **Java Swing**
- **JavaFx**
- **Socket Programming**
- **NetBeans IDE**
- **Client–Server Networking**

---

## 🎯 Academic Purpose
Developed for:

- Software Development 2 (SFD2)
- Diploma in Computer Engineering

Focus Areas:

  Networking & Communication
- GUI Development (Swing & JavaFX)
- Software Architecture & Distributed Systems

---

## 🚀 How to Run

1. Open the project in NetBeans IDE.
2. Build all three applications.
3. Start the Control Room Server first.
4. Run the clients in any order:
- Client Home
- Client Guard

⚠️ Important: Server must be running before any client connects

---

## 📷 Screenshots
### 🖥️ Mkhatjwa_ControlRoomServer
Initial server startup:

<img width="792" height="551" alt="image" src="https://github.com/user-attachments/assets/a3c016f4-2b8d-4d02-aaa4-bdc40bcc81a7" />

---

### 🏠Mkhatjwa_ClientHome
Home-side client interface:

<img width="791" height="637" alt="image" src="https://github.com/user-attachments/assets/360711e1-cc29-41dd-8ca8-40b700c2c70b" />

---

### 🛡️ Mkhatjwa_ClientGuard
Guard-side monitoring interface:

<img width="903" height="468" alt="image" src="https://github.com/user-attachments/assets/8a61f41e-d973-4b1b-a118-24157c209074" />

---

## 👨‍💻 Author

**Musawenkosi Mkhatjwa**  

---

## ✅ Notes

This project demonstrates practical implementation of distributed systems, client-server communication and Java GUI application development.

It’s designed for academic purposes and is part of my final-year portfolio.
