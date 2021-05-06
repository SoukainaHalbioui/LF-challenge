# labforward-challenge

Time spent: approximately 5 hours in total, excluding break times.

Scope of the solution: implements the user story, takes into account the use of punctuation inside entries, handles empty or missing entries/keywords in the request.

I used Maven as a build tool and Intellij as an IDE to build the project. When run locally, the endpoint URL is "http://localhost:8080/entry-analysis/frequency".
The request body object should contain two string attributes: "entry" and "word".
The API endpoint would return a response that contains an integer representing the number of occurences of "word" in "entry", and a list 
of strings that contains similar words.

Things I would improve in my solution if I had more time would be the efficiency of the algorithm that calculates the edit distance in terms of space complexity.
I used a matrix of size n * m (n and m being the lengths of the two strings being compared) for each word within the entry. This wouldn't be an issue for somewhat 
short entries like the ones I used to test my solution, but it would for entries containing thousands of words as it would consume a significant amount of memory storage. 
I would have tried to implement a solution that carries out the algorithm while only storing the last two columns that contains the answer for the distance.

As to the UI, please find a file titled "Example_UI.jpg" (directly under SoukainaHalbioui/labforward-challenge) that shows my suggestion for a UI that exposes 
this simple service.

Looking forward to your feeback :)
