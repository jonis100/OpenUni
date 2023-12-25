;(module sec2.5 (lib "eopl.ss" "eopl")
#lang eopl

(require "utils.scm")

(define-datatype prefix-exp prefix-exp?
  (const-exp
   (num integer?))
  (diff-exp
   (operand1 prefix-exp?)
   (operand2 prefix-exp?)))

  
(define parse-prefix
  (lambda (datum)
    (cond
      ((integer? (car datum))
       (cons (const-exp (car datum)) (cdr datum)))
      ((eqv? (car datum) '-)
       (letrec
        ((op1 (parse-prefix (cdr datum)))
         (op2 (parse-prefix (cdr op1))))
        (cons (diff-exp (car op1) (car op2)) (cdr op2))))
      (else (eopl:error 'parse-prefix "bad syntax"))))
      )
  
;)
