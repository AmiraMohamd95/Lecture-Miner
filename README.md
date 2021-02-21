# Lecture Miner


- Android App that converts audio for lectures into text, extracts important keywords from the lecture, and recommends recent 
  professional courses.  
- Microsoft cognitive services are used (text to speech, text analytics, bing search APIs)
- Project proposal: https://goo.gl/NfdNNQ


- Three stages:

  - Stage 1: Speech recognition using Microsoft Speech recognition API which takes audio input and transfer it into text.

  - Stage 2: Text extraction using Microsoft Text Analytics API which extracts vital keywords.
  
  - Stage 3: Web crawling using Jsoup Web crawling API to retirieve online results of the keywords.
