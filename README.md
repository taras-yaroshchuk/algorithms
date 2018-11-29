Algorithms runner App.

App read file with algorithm and it's input, process it and store result in single csv file.
Sample of input file is located in `src/test/resources/algorithms.csv`

App could be build with simple "mvn clean install" command, that will generate fat jar.
After that, app could be executed by running `java -jar target/algorithms.runner-1.0.0-jar-with-dependencies.jar src/test/resources/algorithms.csv`
Main class is "com.sigma.testApp". Path to csv file with algorithms info should be passed as first program arg.
[optional] Output path could be passed through second arg


Restoring whitespaces is implemented using Trie data structure with few modifications:
 - "get" method return node even if its not final (even if its not a whole word, but subword).
 - classic Trie use array of child nodes for each node, but it require to init N*256 arrays, where N - tree depth.
 In order to save memory this implementation use Map for storing link to children nodes..
 
 First step of algorithm is building a classic Trie from dictionary words.
 Second step is iterating over each char of input string and comparing it to same char array in Trie.


As far as merge sorting is still one of the fastest algorithms, it was implemented using generic comparable type. 
It could be modified in future to use insertion sorting for small arrays.

Sample input:
```
SORT,src/main/resources/tosort.csv
WHITESPACES,src/test/resources/restore_whitespaces.csv,src/test/resources/restore_whitespaces_dictionary.csv
SORT,invalid
WHITESPACES,invalid
```

Sample output:
```$xslt
SORT,[10,5,4,3,2,1]
WHITESPACES,[карл у клары украл кораллы]
SORT,[Error while reading input array]
WHITESPACES,[Path to file with dictionary should be passed as second program arg]

```
A lot of bossiness logic was covered by unit tests using JUnit testing framework.

For future:
- add more test cases
- update algorithm to TimSort