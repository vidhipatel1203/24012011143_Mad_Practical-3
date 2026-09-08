# Practical-3: Implicit and Explicit Intent

## Aim

To develop an Android application that demonstrates the use of **Implicit Intent** and **Explicit Intent** for performing different operations and navigating between activities.

---

## Application Demo

<table width="100%">
<tr>
<td width="50%" valign="top">

### 🎥 Demo

[▶️ Watch Practical 3 Demo](https://github.com/vidhipatel1203/24012011143_Mad_Practical-3/blob/master/demo.mp4)

</td>

<td width="50%" valign="top">

### 📝 Operations

1. **Open Website**
2. **Phone Dialer**
3. **Call History**
4. **Gallery**
5. **Camera**
6. **Set Alarm**
7. **Login Activity**

</td>
</tr>
</table>

---

## Intent Implementation

### 1. Implicit Intent

Implicit Intent is used when we want Android to perform an action without specifying a particular application or component.

### Web Browser

```kotlin
findViewById<Button>(R.id.btn_Browse).setOnClickListener {
    val url = findViewById<EditText>(R.id.editTextText).text.toString()
    Intent(Intent.ACTION_VIEW, url.toUri()).also {
        startActivity(it)
    }
}
```

The `ACTION_VIEW` intent opens the entered website in an available web browser.

### Phone Dialer

```kotlin
findViewById<Button>(R.id.btn_Call).setOnClickListener {
    val number = findViewById<EditText>(R.id.editTextText2).text.toString()
    Intent(Intent.ACTION_DIAL).apply {
        data = "tel:$number".toUri()
    }.also {
        startActivity(it)
    }
}
```

The `ACTION_DIAL` intent opens the phone dialer with the entered number.

### Alarm

```kotlin
findViewById<Button>(R.id.btn_Alarm).setOnClickListener {
    Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_HOUR, 7)
        putExtra(AlarmClock.EXTRA_MINUTES, 30)
        putExtra(AlarmClock.EXTRA_MESSAGE, "Wake Up")
    }.also {
        startActivity(it)
    }
}
```

The `AlarmClock` intent opens the device's alarm application and sets the specified time and message.

The application also uses implicit intents to access the **call log, gallery, and camera**.

---

### 2. Explicit Intent

Explicit Intent is used when the destination component is already known. It is commonly used to move from one Activity to another Activity within the same application.

### Login Activity Navigation

```kotlin
findViewById<Button>(R.id.btn_Login).setOnClickListener {
    Intent(this, LoginActivity::class.java).also {
        startActivity(it)
    }
}
```

In this example, `LoginActivity` is directly specified as the target Activity.

---

## UI Details

### Main Activity

The main screen is designed using `ConstraintLayout` and contains the controls required for demonstrating different intents.

It includes:

* URL input field
* Phone number input field
* Browse button
* Call button
* Call Log button
* Gallery button
* Camera button
* Alarm button
* Login button

---

### Login Activity

The login screen provides a simple user interface containing:

* University logo
* Email input field
* Password input field
* Login button
* Forgot Password option
* `MaterialCardView` for the login form

---

## Student Details

| Details            | Information                          |
| ------------------ | ------------------------------------ |
| **Enrollment No.** | 24012011143                          |
| **Practical**      | 03                                   |
| **Subject**        | Mobile Application Development (MAD) |

---

## Conclusion

The Practical 3 application successfully demonstrates **Implicit and Explicit Intent** in Android.

Implicit intents are used for operations such as opening a website, launching the phone dialer, viewing call history, accessing the gallery and camera, and setting an alarm. Explicit intent is used to navigate from the Main Activity to the Login Activity.

This practical helped in understanding how intents are used to perform system-level actions and navigate between different components of an Android application.
