# EY-FullStack-Internship2025

<!-- BADGES -->
![Java](https://img.shields.io/badge/Java-17-blue) ![License](https://img.shields.io/badge/License-MIT-green)

# 🖼️ Canvas & Rectangles Processor

A lightweight Java console application that reads a list of named rectangles from a file, analyzes their positions against a user-defined canvas, and outputs:

1. **a)** Rectangles completely inside the canvas  
2. **b)** Among those, which do **not** overlap any other  
3. **c)** Among those, which are fully contained in another rectangle  
4. **e)** Remaining uncovered area on the canvas  

---

## 🌟 Features

- ✅ **Parse** rectangle definitions from text file  
- ✅ **Validate** dimensions & skip invalid entries  
- ✅ **Detect** containment vs. overlap vs. full inclusion  
- ✅ **Compute** uncovered canvas area  
- 📝 **Detailed logging** via SLF4J + Logback  
- 💾 **File I/O** with buffered reader  

---

## 🛠️ Tech Stack

| Component         | Technology            |
| ----------------- | --------------------- |
| Language          | Java 17               |
| Build             | Maven                 |
| Logging           | SLF4J + Logback       |
| File I/O          | `java.io`             |
| Data Structures   | `List`, `Map`, arrays |

---

## 📂 Project Structure
CanvasRectangleApp/
├─ pom.xml
├─ logback.xml
└─ src/
├─ main/
│ ├─ java/
│ │ ├─ domain/
│ │ │ ├─ Rectangle.java
│ │ │ └─ Main.java
│ └─ resources/
│ └─ input.txt ← rectangle definitions
└─ test/ ← (optional unit tests)


---

## 📐 Architecture Overview

```mermaid
flowchart LR
  A[Start: Read Canvas Size] --> B[Load & Parse input.txt]
  B --> C{Valid Rectangle?}
  C -- No --> B
  C -- Yes --> D[Store Rectangle]
  D --> E[Filter: completely in canvas]
  E --> F[a) List insideCanvas]
  F --> G[b) Non-overlapping subset]
  F --> H[c) Fully contained subset]
  E --> I[e) Compute uncovered area via grid scan]
  G & H & I --> J[Print Results]
  J --> K[End]
```

## 📥 Getting Started

1. Clone:
    git clone https://github.com/yourusername/CanvasRectangleApp.git
    cd CanvasRectangleApp
2. Build:
    mvn clean package
3. Prepare Input:
    Edit src/main/resources/input.txt.
    Format: Name X Y Width Height
    e.g. A 2 1 3 3  
         B 4 3 3 3    
         ...
4. Run:
    java -jar target/CanvasRectangleApp-1.0-SNAPSHOT.jar

## ▶️ Usage Example
Enter the width of the canvas:
> 10
Enter the height of the canvas:
> 10

a):
Rectangle{nameOfRectangle = A, coordinateX = 2, coordinateY = 1, width = 3, height = 3} ,
...  

b):
Rectangle{nameOfRectangle = I, coordinateX = 5, coordinateY = 6, width = 2, height = 2} ,

c):
Rectangle{nameOfRectangle = R, coordinateX = 1, coordinateY = 1, width = 1, height = 1} ,

e): 58
