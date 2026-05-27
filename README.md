# Cat & Mouse Game

A 2D tile-based game developed in Java as a school project. This project demonstrates strong proficiency in Object-Oriented Design (OOD) and handling low-level game mechanics, including user input and collision logic.

## Overview

When starting the game, the mouse will start to run away from the player (cat).
<img width="769" height="596" alt="image" src="https://github.com/user-attachments/assets/12775482-7e6c-482c-a0ba-4f6e7d877bfb" />

If you try to run into walls (brick or wood) or water you will come to a stop due to collision.
<img width="657" height="501" alt="image" src="https://github.com/user-attachments/assets/b3c9aafd-d96a-4b49-b1c8-29e00082fe1c" />

When you catch the mouse you win and will get the option to restart the game.
<img width="768" height="570" alt="image" src="https://github.com/user-attachments/assets/f40ffe49-0335-416a-9d4a-4357d3c75cf4" />


## Key Features

* **Platform Implementation:** Designed and developed the application window and graphical interface using Java Swing and MigLayout.
* **Object-Oriented Design (OOD):** Implemented a modular and clean architecture where the game logic is strictly separated into dedicated modules (e.g., `Entity`, `TileManager`, `CollisionControll`).
* **Advanced Game Logic:** Implemented core game mechanics including complex tile-based collision handling to manage player/mouse interaction with the game environment.
* **Resource Management:** Utilized Gson for structured data handling (e.g., map configuration or data persistence).


## How to Run

This project requires a Java Development Kit (JDK) installed (version 17 or higher recommended).

1.  **Clone the Repository:**
    ```bash
    git clone [REPOSITORY_URL]
    ```
2.  **Open in IDE:** Open the project in a Java IDE (IntelliJ IDEA recommended).
3.  **Configure Libraries:** Ensure the necessary external libraries (MigLayout and Gson) are included in the project's build path (or configured via Maven/Gradle if applicable). (Note: These libraries are located in the `/libs` directory of the project.)
4.  **Run Main Class:** Execute the `Main.java` class located in the `src/se/liu/vicbe988/background` package.
