# Natural Disasters Data Analysis

A Java desktop application for loading, analyzing, searching, and exporting natural disaster statistics from CSV and TSV datasets.

The project focuses on climate-related disaster data and provides functionality for data loading, statistical analysis, search operations, regression calculations, and report generation in multiple formats.

## Features

- Load natural disaster datasets from CSV and TSV files
- Represent disaster measurements using domain model classes
- Search disaster records by country, indicator, and year range
- Calculate descriptive statistics
- Perform regression calculations
- Display data in a Java Swing graphical interface
- Export reports in multiple formats:
  - Text
  - Markdown
  - HTML
- Includes unit tests for loading, searching, statistics, regression, and report generation

## Technologies

- Java
- Java Swing
- JUnit
- Apache Commons Math
- CSV/TSV file processing
- Object-oriented design

## Project Structure

```text
natural-disasters-data-analysis/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── app/
│   │   │   ├── domain/
│   │   │   ├── dom2app/
│   │   │   ├── engine/
│   │   │   ├── LoadFile/
│   │   │   ├── Saving/
│   │   │   ├── Search/
│   │   │   └── Statistics/
│   │   └── resources/
│   │       └── InputData/
│   └── test/
│       ├── java/
│       └── resources/
└── README.md
```

