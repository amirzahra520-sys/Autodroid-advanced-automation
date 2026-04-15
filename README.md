# AutoDroid: Advanced Automation Engine

AutoDroid is a production-ready Android application designed for on-device automation. It leverages Android's Accessibility Service and ML Kit for OCR to provide a powerful, cloud-free automation experience.

## Features

- **Accessibility Engine**: Click, read, and interact with any UI element.
- **On-Device OCR**: Real-time text recognition using ML Kit.
- **Variable System**: Dynamic variables with conditional logic (IF/ELSE/LOOP).
- **Macro Recorder**: Record and playback user interactions.
- **Clean Architecture**: Built with MVVM, Room, and Jetpack Compose.

## Project Structure

- `app/src/main/java/com/autodroid/automation/service`: Accessibility Service implementation.
- `app/src/main/java/com/autodroid/automation/engine`: Execution engine for scripts.
- `app/src/main/java/com/autodroid/automation/ocr`: ML Kit OCR integration.
- `app/src/main/java/com/autodroid/automation/data`: Room database for scripts and variables.
- `app/src/main/java/com/autodroid/automation/ui`: Jetpack Compose UI.

## How to Build

1. **Prerequisites**:
   - Android Studio Hedgehog or newer.
   - JDK 17.
   - Android SDK 34.

2. **Steps**:
   - Clone or download this project.
   - Open the project in Android Studio.
   - Wait for Gradle sync to complete.
   - Connect an Android device (API 26+).
   - Click **Run** or use the terminal:
     ```bash
     ./gradlew assembleDebug
     ```
   - The APK will be located at `app/build/outputs/apk/debug/app-debug.apk`.

3. **Setup**:
   - After installation, open the app.
   - Grant **Accessibility Permission** when prompted (or go to Settings > Accessibility > AutoDroid).
   - Grant **Overlay Permission** if required for floating controls.

## Example Automation Script

The following logic is implemented in the `ExecutionEngine`:

1. **OCR Scan**: Detects text on screen.
2. **Variable Assignment**: `status = "Connected"` if text found.
3. **Conditional Branching**: `IF status == "Connected" THEN CLICK "Start"`.
4. **Loop**: `WHILE running DO DELAY 1000`.

## Compliance

This app is designed for automation and accessibility. Ensure you comply with the terms of service of any third-party applications you automate.
