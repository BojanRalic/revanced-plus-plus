# Revanced++ Patches

Enhanced patches for Android apps. A fork of ReVanced Patches with code quality improvements and extended features.

## What is Revanced++?

Revanced++ modifies Android apps (YouTube, Spotify, Reddit, and 70+ more) to:

- **Remove ads** — YouTube, Spotify, Reddit, and more
- **Background playback** — YouTube audio/video while screen is off
- **SponsorBlock** — Skip sponsored segments in YouTube videos
- **Return YouTube Dislike** — Show dislike counts again
- **Custom branding** — Change app name and icon
- **Privacy** — Block tracking, hide ADB status, spoof SIM
- **UI customization** — Hide/show elements, themes, seekbar colors
- **And much more** — 70+ supported apps, hundreds of patches

## Installation

### Step 1 — Download ReVanced Manager

Download from: [github.com/ReVanced/revanced-manager/releases/latest](https://github.com/ReVanced/revanced-manager/releases/latest)

Install the `.apk` file on your Android phone. If asked, allow installation from unknown sources.

### Step 2 — Add Revanced++ as a source

In ReVanced Manager:
1. Open **Settings** → **Sources**
2. Tap **Add source**
3. Enter this URL:
   ```
   https://github.com/CodeVibing/revanced-plus-plus/releases/latest/download/patches.rvp
   ```

### Step 3 — Download YouTube APK

Download YouTube version **20.14.43** (nodpi) from:
[apkmirror.com/apk/google-inc/youtube/youtube-20-14-43-release/](https://www.apkmirror.com/apk/google-inc/youtube/youtube-20-14-43-release/)

### Step 4 — Patch and install

1. In ReVanced Manager: **Patcher** → **Select an application** → **Storage**
2. Choose the YouTube APK you downloaded
3. Select your desired patches
4. Tap **Patch** and wait (~3-5 minutes)
5. Tap **Install**

## Building from source

```bash
git clone https://github.com/CodeVibing/revanced-plus-plus
cd revanced-plus-plus
./gradlew :patches:buildAndroid
```

Requires JDK 17 and Android SDK.

## License

GPL-3.0 — see [LICENSE](LICENSE)
