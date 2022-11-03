(module interp (lib "eopl.ss" "eopl")
  
  ;; interpreter for the LET language.  The \commentboxes are the
  ;; latex code for inserting the rules into the code in the book.
  ;; These are too complicated to put here, see the text, sorry.

  (require "drscheme-init.scm")
  (require "lang.scm")
  (require "data-structures.scm")
  (require "environments.scm")

  (provide value-of-program value-of)

;;;;;;;;;;;;;;;; the interpreter ;;;;;;;;;;;;;;;;

  ;; value-of-program : Program -> ExpVal

  (define value-of-program 
    (lambda (pgm)
      (cases program pgm
        (a-program (exp1)
          (value-of exp1 (init-env))))))

  ;; value-of : Exp * Env -> ExpVal

  (define value-of
    (lambda (exp env)
      (cases expression exp

        ;\commentbox{ (value-of (const-exp \n{}) \r) = \n{}}
        (const-exp (num) (num-val num))

        ;\commentbox{ (value-of (var-exp \x{}) \r) = (apply-env \r \x{})}
        (var-exp (var) (apply-env env var))

        ;\commentbox{\diffspec}
        (diff-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (num-val
                (- num1 num2)))))

         (mul-exp (exp1 exp2)
          (let ((val1 (value-of exp1 env))
                (val2 (value-of exp2 env)))
            (let ((num1 (expval->num val1))
                  (num2 (expval->num val2)))
              (num-val
                (* num1 num2)))))
        
        ;\commentbox{\zerotestspec}
        (zero?-exp (exp1)
          (let ((val1 (value-of exp1 env)))
            (let ((num1 (expval->num val1)))
              (if (zero? num1)
                (bool-val #t)
                (bool-val #f)))))
        
        
        (plus-exp (exps)
                   (let ((vals (helper exps env)))
                      (num-val (eval (cons '+ vals)))))
              
        ;\commentbox{\ma{\theifspec}}
        (if-exp (exp1 exp2 exp3)
          (let ((val1 (value-of exp1 env)))
            (if (expval->bool val1)
              (value-of exp2 env)
              (value-of exp3 env))))

        ;\commentbox{\ma{\theletspecsplit}}
        (let-exp ( vars exps body)
                         
          (let ((vals (values-of-exps  exps env)))
            (value-of body
              (extend-env  vars vals env))))
        
        (proc-exp (ids defs body)
           (let* ([valdefs (values-of-exps defs env)]
                  [extenv (extend-env  ids valdefs env)])
             (proc-val (procedure ids valdefs body extenv))
           ))
          ;(proc-val (procedure vars body env)))

        (call-exp (rator parms-vars parms-exps)
            (let* ([proc (expval->proc (value-of rator env))]
                 [val-exps (values-of-exps parms-exps env)])
                (apply-procedure proc parms-vars val-exps)))
          ;(let ((proc (expval->proc (value-of rator env)))
            ;    (args (map (lambda (x) (value-of x env)) rands)))
            ;(apply-procedure proc args)))

        
       

        )))
  
  (define (helper exps env) 
      (map  (lambda (x)  (expval->num (value-of x env))) exps))
  
  (define values-of-exps
    (lambda (exps env)
      (map (lambda(x) (value-of x env)) exps)))
  
    ;; apply-procedure : Proc * ExpVal -> ExpVal
  ;; Page: 79
  (define apply-procedure
    (lambda (proc1 parms-vars val-exps)
      (cases proc proc1
        (procedure (ids defs body saved-env)
            (if (not (= (length val-exps) (length parms-vars)))
                (eopl:error "Error: wrong call exp")
                
                (value-of body (extend-env parms-vars val-exps saved-env)))))))
                 ;(value-of body (extendmulti saved-env parms-vars val-exps )))))))
    
    
    (define (extendmulti e l1 l2)
      (if (null? l1)
          e
        (extendmulti    (extend-env (car l1) (car l2) e)  (cdr l1)  (cdr l2))))
  

  )

