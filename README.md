# ✈️ 2D Arcade Defense Game (Java)

This project is a **2D arcade-style defense game** developed in **Java** using the **ShapesGE** graphical library.  
The player controls a character on the deck of a ship and must defend it against multiple waves of enemy aircraft.

The game was created as a **student project** with a strong focus on **Object-Oriented Programming (OOP)**, game logic, and basic software architecture.

---

## 🎮 Game Description

The player moves on the ship’s deck and defends it against incoming enemies.  
Different enemy types attack in waves, each with unique behavior and attack logic.

The player can switch between two weapons:
- **Machine Gun** – fast fire rate, lower damage
- **Rocket Launcher** – slower fire rate, higher damage

The game ends in one of two states:
- **Victory** – all enemies are destroyed
- **Defeat** – the ship’s HP reaches zero

After the game ends, a **win screen or end screen** is displayed.

---

## 🧠 Main Features

- Object-oriented architecture with clear separation of responsibilities
- Central game logic manager (`GameManager`)
- Collision detection system based on object distance
- Weapon cooldown system
- Multiple enemy types with different behaviors
- Start screen, win screen, and end screen
- Persistent high score system stored in a file
- UML class diagram (external view)

---

## 🏗️ Project Architecture

The project is divided into logical components:

- `Hra` – initializes the game and starts the level
- `GameManager` – core game logic (collisions, timers, score, win/lose conditions)
- `Hrac` – player logic (movement, shooting, weapon switching)
- `Lod` – represents the ship and its health points
- Enemy classes – different types of enemy aircraft
- Projectile classes – bullets and rockets
- Screen classes – start screen, win screen, end screen

This structure improves readability, maintainability, and extensibility of the code.

---

## 🕹️ Controls

| Key | Action |
|----|-------|
| Arrow keys | Move player |
| Space | Shoot |
| P | Switch weapon |
| Enter | Start game |
| 2x Esc | Exit game |

---

## ⚙️ Technologies Used

- **Java**
- **ShapesGE** (graphics and game loop)
- Object-Oriented Programming (OOP)
- File I/O (high score saving)
- UML (design documentation)

---

## ▶️ How to Run the Game

### Option 1: Run from IDE (recommended)

1. Open the project in **IntelliJ IDEA**
2. Make sure the `assets` folder is in the project root
3. Run `Main.java`

### Option 2: Run from JAR

Make sure the folder structure looks like this:
game.jar
assets/



📁 Assets

All game graphics (backgrounds, sprites, explosions) are stored in the assets/ folder.
The game uses relative paths, so the assets folder must be present in the working directory.

---

📊 UML & Design

The project was designed using UML class diagrams focusing on:
	•	Encapsulation
	•	Associations
	•	Composition
	•	Clear separation of responsibilities

---

🚀 Possible Improvements
	•	Refactor GameManager into smaller manager classes
	•	Improve collision detection accuracy
	•	Add pause and restart functionality
	•	Improve resource loading for JAR execution
	•	Add sound effects and background music


---

### UML – External View (Public Interface)

The UML class diagram included in this project represents the **external view** of the application.  
This means that each class is shown only through its **public interface**, which consists of:

- Public constructors
- Public methods

Private attributes and private helper methods are intentionally omitted from the diagram.  
The purpose of this approach is to focus on **how individual classes can be used and interacted with**, rather than on their internal implementation details.

This type of UML diagram clearly shows:
- The responsibilities of each class
- The available operations that other parts of the system can call
- The relationships between classes on an interface level

The external view improves readability and makes the system easier to understand, especially for documentation and design presentation purposes. It also follows good object-oriented design principles by emphasizing **encapsulation**, where internal data and logic are hidden and accessed only through well-defined public methods.


<img width="1158" height="825" alt="UML-AirStrike" src="https://github.com/user-attachments/assets/f8987bf3-a366-4edc-80bc-de53685e194b" />


---

👨‍🎓 Author

This project was developed as a student assignment for an Object-Oriented Programming course.

---

📜 License

This project is licensed under the MIT License.

MIT License

Copyright (c) 2026

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
