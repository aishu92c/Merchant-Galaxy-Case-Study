MERCHANT'S GUIDE TO THE GALAXY



Valid case.
Input
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

Output
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob prok Gold is 57800 Credits
glob prok Iron is 782 Credits
I have no idea what you are talking about

MerchantResponse{
   output=   [
      pish tegj glob glob is 42,
      glob prok Silver is 68 Credits,
      glob prok Gold is 57800 Credits,
      glob prok Iron is 782 Credits,
      I have no idea what you are talking about
   ],
   status='success',
   errors=   [

   ]
}

InValid case


glob is IVVV
prok is V
pish is X
tegj is L
glob xym Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Aishu ?

Output
IVVVVis invalid
MerchantResponse{
   output=   [
      IVVVVis invalid
   ],
   status='failure',
   errors=   {
      glob is IVVVV=INVALID_ROMAN_CHARACTER
   }
}