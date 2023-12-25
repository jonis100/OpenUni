#lang racket

(require "utils.scm")

;----------------------Q1

(define (my_flatten lst)
  (cond ((empty? lst) '())
        ((pair? lst) (append (my_flatten (car lst)) (my_flatten(cdr lst))))
        (else (list lst))
        ))

;----------------------Q2

(define (my_count p lst)
  (cond ((empty? lst) 0)
         ((p (car lst)) (+ (my_count p (cdr lst)) 1))
          (else (+ (my_count p (cdr lst)) 0))
             ))
                       
(define (my_count_foldr p lst)
   (foldr (λ (x res)
          (if (p x)
               (+ 1 res)
               res
           ))
  0 lst))

;----------------------Q3

(define my_partition   
  (λ (pre lst)
      (let-values (((true false) (partition pre lst)))
        (list true false))))

;----------------------Q4

(define my_permutations
  (λ (lst)
  (cond ((empty? lst) empty)
        ((empty? (rest lst)) (list lst))
        (else
         (let splice ((l '()) (m (first lst)) (r (rest lst)))
           (append
            (map (λ (x) (cons m x))
                 (my_permutations (append l r)))
            (if (empty? r)
                empty
                (splice (cons m l) (car r) (cdr r)))))))))
  

;-------------------------------------------------------------------------
;Unit tests

(equal?? (my_flatten '( (1 2) ((3)) (4 5 6)))  '(1 2 3 4 5 6))
(report-unit-tests-completed 'my_flatten)
(equal?? (my_count  positive? '(1 3 -7 9 -2)) 3)
(report-unit-tests-completed 'my_count)
(equal?? (my_count_foldr positive? '(1 3 -7 9 -2)) 3)
(report-unit-tests-completed 'my_count_foldr)
(equal?? (my_partition even? '(1 2 3 4 5 6)) '( (2 4 6) (1 3 5)))
(report-unit-tests-completed 'my_partition)
(equal?? (my_permutations '(1 2 3)) '((1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 2 1) (3 1 2)))
(report-unit-tests-completed 'my_permutations)