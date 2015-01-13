
from PIL import Image 
chars =" ...',;:clodxkLO0DGEKNWMM"
fn=r'C:\Users\Yrh\Pictures\myself.jpg'
f1=lambda F:''.join([(k%100!=0) and m or m+'\n' for k,m in enumerate(apply(lambda x:[chars[x[j,i]%len(chars)] for i in xrange(70) for j in xrange(100)],(Image.open(F).resize((100,70)).convert("L").load(),)),1)])
f=open(r"C:\Users\Yrh\Pictures\result.txt","w")
f.write(f1(fn))
f.close()
