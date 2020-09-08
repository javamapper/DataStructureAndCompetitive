package com.javamapper.problems;

public class PossibleSubSequnces {
	public void recursionApproach() {
		String s = "abcd"; 
		findsubseqRecu(s, "");
		System.out.println("--- EOM ---");
	}
	public void iterationApproach() {
		System.out.println("--- EOM ---");
	}
	private void findsubseqRecu(String input, String result){ 
        if (input.length() == 0) { 
            System.out.println(result);
            return; 
        } 
        //abcd,""
        /* frame 0) 	line1-> abcd,""   back for line 2
         * frame 1) 	line1-> bcd,a  	  back for line 2
         * frame 2) 	line1-> cd,ab  	  back for line 2
         * frame 3) 	line1-> d,abc  	  back for line 2
         * frame 4) 	line1-> "",abcd   end s length 0  (frame born from frame 3 line 1)		print=>abcd
         * frame 4) 	end
         * frame 4.1) 	line2-> "",abc    end s length 0  (frame born from frame 3 line 2)		print=>abc
         * frame 3) 	end
         * frame 3.1) 	line1-> d,ab	  back for line 2 (frame born from frame 2 line 2)
         * frame 4.2) 	line1-> "",abd	  end s length 0  (frame born from frame 3.1 line 1)	print=>abd
         * frame 4.2) 	end
         * frame 3.1) 	line2-> "",ab 	
         * frame 4.3) 	line1-> "",ab     end s length  0 (frame born from frame 3.1 line 2) 	print=>ab
         * frame 4.3) 	end
         * frame 3.1) 	end  
         * frame 2) 	end
         * frame 1) 	line2-> cd,a	 back for line 2
         * frame 2.1)	line1-> cd,a	 back for line 2
         * frame 3.3)	line1-> d,ac	 back for line 2
         * frame 4.4)	line1-> "",acd	 end s length  0	
         * frame 3.3)   line2-> "",ac
         * frame 4.5)	line1-> "",ac 	 end s length  0
         * frame 4.5)	end
         * frame 3.3)	end
         * frame 2.1) 	line2->d,a
         * frame 3.4)	line1->d,a
         * frame 4.6)	line1->"",ad	 end s length 0 
         * frame 4.6)	end	
         * frame 3.4)   line2->"",a		 
         * frame 4.7)	line1->"",a		 end s length 0
         * frame 4.7) 	end
         * frame 3.4)   end 
         * frame 2.1) 	end
         * frame 1)		line2->bcd,""
         * ...
         */
        //subtract first index char from s and add first index char + ans(value) 
        findsubseqRecu(input.substring(1), result + input.charAt(0)); 
        //subtract first index char from s and keep ans(value) as it is 
        findsubseqRecu(input.substring(1), result); 
    } 
}
