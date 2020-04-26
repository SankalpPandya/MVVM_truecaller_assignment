
# TrueCallerAssesment_Project

 Application Architecture-----------------------------------------------------------------------------------------------------------

- This application architecture follows the MVVM architecture , which provides reactive , lifecycle aware and smooth implementation flow.
- For achieveing this  RxJava2 along with Retrofit are used.
- For backward compatibility androidx has been used.
- Application provides flexibility to provide dynamic urls for web requsts as retrofit API has URL has parameters.

Execution flow:-----------------------------------------------------------------------------------------------------------------------

- App contains only one activity which adds default fragment containing the button and 3 edittexts.
- Once the default fragment is added , corrosponding ViewModel is initiated which holds a reference to Repository(Resposible for making netword call using retrofit).
- When trigger request button is initiated 3 simultanous requests ar view model , default fragment acts as a observer for all those request responses.
- When observable responses from the network are received view model takes the resposibility to update the texviews in the fragment while considering the lifecycle state of Activity.

Current Output for given url :---------------------------------------------------------------------------------------------------------

1. Truecaller10thCharacterRequest: 
 --> returned the 10th Character of the html response received which with given url returns html response as <!DOCTYPE  so 10th character output is a space character“ ”

2. TruecallerEvery10thCharacterRequest:
---> For this point, returned a sorted hashmap by key with index as key and character at the index as value  of the html response received.

3. TruecallerWordCounterRequest:
----> For this point, returned the sorted hashmap with index as words and its count in response as value of the html response callback.

--> For all these business logic , the helper classes are created and they follow singleton pattern. Hence, multiple object creation can be avoided for any number of requests.
--> Here using hashmap servs the purpose by helping us to avoid any logic for duplicate words, as we can add words as key and we would get all distinct words in the keys().

Assumption:

-> Ideally as the URL for all  request was same , we can get the above output through only request. However, as it was mentioned in instruction to perticularly follow 
   3 seperate request I have implemented the same.

-> Currently , the layout is only designed for Portrait mode due to limited time.Hence , perticular orientation mode is request in manifest itself !
