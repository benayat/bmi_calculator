# bmi_calculator
what I want to do:
create a bmi calculator app on android:
the app has two activities(an activity is a screen, which contains interactive components.
each activity consists of two files, a java file and an xml file,
one is the main activity, which we used to get all te input from the user - gender, age, weight and height.
I use the xml file for all the resources - variables, pictures, and graphical settings for the components.
to build the calculator I used radio buttons(https://en.wikipedia.org/wiki/Radio_button) for the gender,
spinner(https://en.wikipedia.org/wiki/Spinner_(computing)) for the age(month or years),
a seekbar(https://developer.android.com/reference/android/widget/SeekBar) for the height,
and edittext(https://developer.android.com/reference/android/widget/EditText) for
height and age.
I added the "calculate" button, to calculate the result and take us to the next activity, "results". the sole purpose of "results" activity
is to display three results: the calculated weight(according to the age based formulas as listed below), the bmi and
weight status. we used textviews(https://developer.android.com/reference/android/widget/TextView) to display the results.
the formulas:
- calculated weight:
For kids: weight = 0/5 * months + 4	 	0 <= age <= 1
 weight = 2 * years + 10			1 <= age <= 10
for other ages:
male weight (kg) = 48 + 1.1 * (h – 152)		h = height in cm
female weight (kg) = 45.4 + 0.9 * (h – 152)	h = height in cm

- bmi formula:
BMI = weight (kg) / height2 (m)

- the program was built with the guidelines of MVC pattern: the model is the "res" directory, which contains all the resources - strings, pictures, etc.
the view is the xml file/s, and the controler is the .java file/s. this separation makes it easier to understand the structure of the program.

Run/close the application
To run the application, open it in android studio, make an emulator, and press "run".
To close the application:  from the second/result screen click the "exit" button.
and then "quit".
