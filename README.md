

# Instant Archive
Instant Archive is an extremely simple Android application that allows you to archive webpages with one click. 
To use Instant Archive:

 1. Select the "Share" icon in whatever application you're in
 2. When prompted with a share dialog, choose the Instant Archive app

It is helpful to [pin Instant Archive to the Android Share menu](https://www.techrepublic.com/article/how-to-pin-apps-to-the-android-share-menu/), so it is always just one click away.

# How it works
The android app has one simple MainActivity. This activity responds to [ACTION_SEND intents](https://developer.android.com/training/sharing/receive), looks for URLs in the shared data, and sends those URLs to archive.today for archiving. 

When the user clicks "Share" while on a webpage or in some application that shares a link, an ACTION_SEND intent is sent to whatever app is chosen in the "Share" dialog. If Instant Archive is chosen, it checks if the data being shared is a string that contains a URL. If it contains a URL, it builds a new URL that tells archive.today to archive the shared webpage. Then it opens this new archive.today URL in the user's default browser.

The entire application is only ~40 lines code added to an empty Android App project, including white space and comments.
