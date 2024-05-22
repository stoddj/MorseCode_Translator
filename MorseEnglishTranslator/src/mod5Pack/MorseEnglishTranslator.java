package mod5Pack;

import java.util.Scanner;

public class MorseEnglishTranslator
{

   public static void main(String[] args)
   {
      MorseEnglishTranslator morseEnglishTranslator = new MorseEnglishTranslator();
      morseEnglishTranslator.chooseLanguage();
   }

   /*
    * Method takes a language to translate to and allows user to input string to
    * convert
    * 
    * @param String that indicates Morse code or English
    * 
    * @return String message to translate into Morse code or English
    */
   public String getInput(String language)
   {
      Scanner input = new Scanner(System.in);
      System.out.print(
            "Please enter a message to translate into " + language + ": ");
      String toTranslate = input.nextLine();
      input.close();
      return toTranslate;
   } // end getInput()

   /*
    * Method allows user to choose to convert bewteen Engligh or Morse code or quit
    * program Program displays the translated message to the console
    */
   public void chooseLanguage()
   {
      final int MORSE = 1;
      final int ENGLISH = 2;
      final int QUIT = 3;
      String message;

      // User input to decided what to translate or exit
      Scanner input = new Scanner(System.in);
      System.out.println(
            "Message Translator: Enter (1) for English to Morse code, (2) for Morse code to English, (3) to quit: ");
      int choice = input.nextInt();

      switch (choice)
      {
      case MORSE:
      {
         message = getInput("Morse code");
         System.out.println("\nYour message '" + message
               + "' translates to the following: ");
         System.out.println(englishToMorse(message));
         break;
      } // end MORSE case
      case ENGLISH:
      {
         message = getInput("English");
         System.out.println("\nYour message '" + message
               + "' translates to the following: ");
         System.out.println(morseToEnglish(message));
         break;
      } // end ENGLISH case
      case QUIT:
      {
         System.out.println("Closing translation program... Goodbye!");
         break;
      } // end QUIT case

      } // end switch

      input.close();
   } // end chooseLanguage()

   /*
    * Method takes String that contains a series of English letters and converts it
    * to Morse code
    * 
    * @param String of alphanumeric characters
    * 
    * @return String message from String array containing translated Morse code
    * message
    */
   public String englishToMorse(String code)
   {
      String msg = "";
      code = code.toUpperCase();
      char[] letterCode = new char[code.length()];
      code.getChars(0, code.length(), letterCode, 0);
      int index = 0;
      char letter;

      // configure array needed to store patterns and spaces after translation
      String[] symbolsTranslated;
      symbolsTranslated = new String[code.length()];

      // parse through input array to pass English letter into method that converts it
      // to Morse letter and stores it in array
      while (index < letterCode.length)
      {
         letter = letterCode[index];
         if (letter == ' ')
         {
            symbolsTranslated[index] = " "; // space indicates new word
         } else
         {
            symbolsTranslated[index] = translateEnglishLetter(letter); // added to see if I can remove the below logic
         }
         index++;
      } // ends while loop to build array to store translated values

      for (int i = 0; i < symbolsTranslated.length; i++)
      {
         if (i == (symbolsTranslated.length - 1))
         {
            msg = msg + symbolsTranslated[i];
         } else
         {
            msg = msg + symbolsTranslated[i] + " ";
         }
      } // ends for loop to generate translated Morse code message
      return msg;
   } // englishToMorse()

   /*
    * Method takes String that contains a series of Morse code letters and converts
    * it to alphanumeric equivalent
    * 
    * @param String of Morse code letters
    * 
    * @return String message from char array containing translated alphanumeric
    * message
    */
   public String morseToEnglish(String code)
   {
      char[] charCode = new char[code.length()];
      code.getChars(0, code.length(), charCode, 0);
      int index = 0;
      int i = 0;
      String symbol = "";
      String msg = "";

      // configure array needed to store letters and space after translation
      int spaceCount = 0; // letter count
      final int ADD_ONE_TO_SPACES = 1; // adds 1 to the space count to account for words to spaces ratio
      char[] lettersTranslated;

      for (int loopIndex = 0; loopIndex < charCode.length; loopIndex++)
      {
         if (charCode[loopIndex] == ' ')
         {
            spaceCount++;
         }

      } // end for loop to determine how many words and how many letters

      // Build array to store translated English letters from Morse code
      lettersTranslated = new char[spaceCount + ADD_ONE_TO_SPACES];

      // parse through input to find Morse code pattern to translate to English letter
      // and store in lettersTranslated array
      while (index < charCode.length)
      {
         if ((charCode[index] == '.') || (charCode[index] == '-'))
         {
            symbol = symbol + charCode[index];
         } // stack symbols that makes the Morse code letter

         if ((charCode[index] == ' ') & (symbol.length() > 0))
         {
            lettersTranslated[i] = translateMorseLetter(symbol);
            i++;
            symbol = "";
         }

         if (charCode[index] == '|')
         {
            lettersTranslated[i] = ' ';
            i++;
         }

         if (index == (charCode.length - 1))
         {
            lettersTranslated[i] = translateMorseLetter(symbol);
            symbol = "";
         } // end if to include final word in message

         index++;
      } // ends while

      for (i = 0; i < lettersTranslated.length; i++)
      {
         msg = msg + lettersTranslated[i];
      } // ends for loop to generate translated Morse code message

      // msg = lettersTranslated.toString();
      return msg;
   } // end morseToEnglish()

   /*
    * Method takes an Morse code character and translate it to a alphanumeric
    * character
    * 
    * @param String of Morse code single alphanumeric character representation
    * 
    * @return char alphanumeric character that matches the Morse code string
    */
   public char translateMorseLetter(String letter)
   {
      char[] englishKey = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
      String[] morseKey = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
            "--..", ".----", "..---", "...--", "....-", ".....", "-....",
            "--...", "---..", "----.", "-----" };

      for (int index = 0; index < morseKey.length; index++)
      {
         if (letter.equals(morseKey[index]))
         {
            char eLetter = englishKey[index];
            return eLetter;
         }
      } // end for that loops through the morseKey to find a letter/digit match
      return (Character) ' ';
   } // end translateMorseLetter that translates the Morse letter into English letter

   /*
    * Method takes an English letter and translate it to a Morse Code character
    * 
    * @param char alphanumeric character
    * 
    * @return String of Morse code character that matches the alphanumeric
    * character
    */
   public String translateEnglishLetter(char letter)
   {
      String mLetter;
      char[] englishKey = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
      String[] morseKey = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
            "--..", ".----", "..---", "...--", "....-", ".....", "-....",
            "--...", "---..", "----.", "-----" };

      for (int index = 0; index < englishKey.length; index++)
      {
         if (letter == englishKey[index])
         {
            mLetter = morseKey[index];
            return mLetter;
         }
      } // end for that loops through the englishKey to find a letter/digit match
      return null;
   } // end translateEnglishLetter that translate the English letter into Morse
     // letter

} // end class MorseEnglishTranslator
